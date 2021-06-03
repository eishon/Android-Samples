package com.lazypotato.volleysampleapp.base;

import androidx.databinding.ViewDataBinding;

public abstract class BaseBindingViewHolder<B extends ViewDataBinding, D> extends BaseViewHolder<D> {
    public B binding;

    public BaseBindingViewHolder(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void unBind() {
        if (binding != null) {
            binding.unbind();
        }
    }
}
