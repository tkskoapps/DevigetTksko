package com.test.dvtest.data;

public class BackendManager {

    private static BackendManager instance;

    public static BackendManager getInstance() {

        if (instance == null)
            instance = new BackendManager();

        return instance;

    }

    public BackendManager() {

    }

}
