package dev.soysix.zerolib.menu.callback;

import java.io.Serializable;

public interface TypeCallback<T> extends Serializable {
   void callback(T var1);
}
