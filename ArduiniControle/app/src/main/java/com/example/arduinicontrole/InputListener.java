package com.example.arduinicontrole;

public interface InputListener {

    void onRead(String message);
    void onError(String error);

}
