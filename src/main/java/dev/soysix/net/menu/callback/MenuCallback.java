package dev.soysix.net.menu.callback;

import java.io.Serializable;

public interface MenuCallback<T> extends Serializable {
    void callback(T data);
}
