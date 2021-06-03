package com.lazypotato.volleysampleapp.data.network.util

import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.lazypotato.volleysampleapp.util.LogUtil
import java.io.*
import java.nio.charset.Charset

class VolleyMultipartRequest : Request<NetworkResponse> {
    private val twoHyphens = "--"
    private val lineEnd = "\r\n"
    private val boundary = "api-client-" + System.currentTimeMillis()
    private var mListener: Response.Listener<NetworkResponse>
    private var mErrorListener: Response.ErrorListener
    private var mHeaders: Map<String, String>? = null

    /**
     * Default constructor with predefined header and post method.
     *
     * @param url           request destination
     * @param headers       predefined custom header
     * @param listener      on success achieved 200 code from request
     * @param errorListener on error http or library timeout
     */
    constructor(
        url: String?, headers: Map<String, String>?,
        listener: Response.Listener<NetworkResponse>,
        errorListener: Response.ErrorListener
    ) : super(Method.POST, url, errorListener) {
        mListener = listener
        this.mErrorListener = errorListener
        mHeaders = headers
    }

    /**
     * Constructor with option method and default header configuration.
     *
     * @param method        method for now accept POST and GET only
     * @param url           request destination
     * @param listener      on success event handler
     * @param errorListener on error event handler
     */
    constructor(
        method: Int, url: String?,
        listener: Response.Listener<NetworkResponse>,
        errorListener: Response.ErrorListener
    ) : super(method, url, errorListener) {
        mListener = listener
        this.mErrorListener = errorListener
    }

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        return if (mHeaders != null) mHeaders!! else super.getHeaders()
    }

    override fun getBodyContentType(): String {
        return "multipart/form-data;boundary=$boundary"
    }

    @Throws(AuthFailureError::class)
    override fun getBody(): ByteArray? {
        val bos = ByteArrayOutputStream()
        val dos = DataOutputStream(bos)
        try {
            // populate text payload
            val params = params
            if (params != null && params.size > 0) {
                textParse(dos, params, paramsEncoding)
            }

            // populate data byte payload
            val data = byteData
            if (data != null && data.size > 0) {
                dataParse(dos, data)
            }

            // close multipart form data after text and file data
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd)
            //dos.flush();
            return bos.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Custom method handle data payload.
     *
     * @return Map data part label with data byte
     * @throws AuthFailureError
     */
    @get:Throws(AuthFailureError::class)
    protected val byteData: Map<String, DataPart>?
        protected get() = null

    override fun parseNetworkResponse(response: NetworkResponse): Response<NetworkResponse> {
        return try {
            Response.success(
                response,
                HttpHeaderParser.parseCacheHeaders(response)
            )
        } catch (e: Exception) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: NetworkResponse) {
        mListener.onResponse(response)
    }

    override fun deliverError(error: VolleyError) {
        mErrorListener.onErrorResponse(error)
    }

    /**
     * Parse string map into data output stream by key and value.
     *
     * @param dataOutputStream data output stream handle string parsing
     * @param params           string inputs collection
     * @param encoding         encode the inputs, default UTF-8
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun textParse(
        dataOutputStream: DataOutputStream,
        params: Map<String, String>,
        encoding: String
    ) {
        try {
            for ((key, value) in params) {
                buildTextPart(dataOutputStream, key, value)
            }
        } catch (uee: UnsupportedEncodingException) {
            throw RuntimeException("Encoding not supported: $encoding", uee)
        }
    }

    /**
     * Parse data into data output stream.
     *
     * @param dataOutputStream data output stream handle file attachment
     * @param data             loop through data
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun dataParse(dataOutputStream: DataOutputStream, data: Map<String, DataPart>) {
        for ((key, value) in data) {
            buildDataPart(dataOutputStream, value, key)
        }
    }

    /**
     * Write string data into header and data output stream.
     *
     * @param dataOutputStream data output stream handle string parsing
     * @param parameterName    name of input
     * @param parameterValue   value of input
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun buildTextPart(
        dataOutputStream: DataOutputStream,
        parameterName: String,
        parameterValue: String
    ) {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd)
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"$parameterName\"$lineEnd")
        //dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
        dataOutputStream.writeBytes(lineEnd)
        dataOutputStream.writeBytes(parameterValue + lineEnd)
    }

    /**
     * Write data file into header and data output stream.
     *
     * @param dataOutputStream data output stream handle data parsing
     * @param dataFile         data byte as DataPart from collection
     * @param inputName        name of data input
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun buildDataPart(
        dataOutputStream: DataOutputStream,
        dataFile: DataPart,
        inputName: String
    ) {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd)
        dataOutputStream.writeBytes(
            "Content-Disposition: form-data; name=\"" +
                    inputName + "\"; filename=\"" + dataFile.fileName + "\"" + lineEnd
        )
        if (dataFile.type != null && !dataFile.type!!.trim { it <= ' ' }.isEmpty()) {
            dataOutputStream.writeBytes("Content-Type: " + dataFile.type + lineEnd)
        }
        dataOutputStream.writeBytes(lineEnd)
        val fileInputStream = ByteArrayInputStream(dataFile.content)
        var bytesAvailable = fileInputStream.available()
        val maxBufferSize = 1024 * 1024
        var bufferSize = Math.min(bytesAvailable, maxBufferSize)
        val buffer = ByteArray(bufferSize)
        var bytesRead = fileInputStream.read(buffer, 0, bufferSize)
        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize)
            bytesAvailable = fileInputStream.available()
            bufferSize = Math.min(bytesAvailable, maxBufferSize)
            bytesRead = fileInputStream.read(buffer, 0, bufferSize)
            LogUtil.debug(
                TAG, """
     DATA OUT STREAM:
     $maxBufferSize
     $bufferSize
     $bytesAvailable
     ${buffer.toString(Charset.forName("UTF-8"))}
     
     
     """.trimIndent()
            )
        }
        dataOutputStream.writeBytes(lineEnd)
    }

    /**
     * Simple data container use for passing byte file
     */
    inner class DataPart {
        /**
         * Getter file name.
         *
         * @return file name
         */
        /**
         * Setter file name.
         *
         * @param fileName string file name
         */
        var fileName: String? = null
        /**
         * Getter content.
         *
         * @return byte file data
         */
        /**
         * Setter content.
         *
         * @param content byte file data
         */
        lateinit var content: ByteArray
        /**
         * Getter mime type.
         *
         * @return mime type
         */
        /**
         * Setter mime type.
         *
         * @param type mime type
         */
        var type: String? = null

        /**
         * Default data part
         */
        constructor() {}

        /**
         * Constructor with data.
         *
         * @param name label of data
         * @param data byte data
         */
        constructor(name: String?, data: ByteArray) {
            fileName = name
            content = data
        }

        /**
         * Constructor with mime data type.
         *
         * @param name     label of data
         * @param data     byte data
         * @param mimeType mime data like "image/jpeg"
         */
        constructor(name: String?, data: ByteArray, mimeType: String?) {
            fileName = name
            content = data
            type = mimeType
        }
    }

    companion object {
        private const val TAG = "VOLLEY MULTIPART REQUEST"
    }
}