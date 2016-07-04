package ru.mongugay.promodjradio.model;

/**
 * Created by user on 01.07.2016.
 */
public class PDRadioModel {

    public String get_name() {
        return _name;
    }

    /**
     * название
     */
    private String _name = "";

    public String get_url() {
        return _url;
    }

    /**
     * Ссылка на радио
     */
    private String _url  = "";

    private String _img = "";

    public PDRadioModel(String name, String url)
    {
        this._name = name;
        this._url  = url;
    }
}
