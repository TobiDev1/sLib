package dev.soysix.net.crate;

import java.io.Serializable;

public interface Callback<T> extends Serializable {
    void callback(T var1);
}
