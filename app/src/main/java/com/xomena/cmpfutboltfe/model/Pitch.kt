package com.xomena.cmpfutboltfe.model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import org.json.JSONArray
import org.json.JSONException

class Pitch : Parcelable {
    var county: String? = null
        private set
    var name: String? = null
        private set
    var address: String? = null
        private set
    var description: String? = null
        private set
    var phone: String? = null
        private set
    var lat = 0.0
        private set
    var lng = 0.0
        private set
    var type: String? = null
        private set
    var placeId: String? = null
        private set
    var accessLat = 0.0
        private set
    var accessLng = 0.0
        private set

    /**
     * Constructor
     * @param js_val JSONArray from data source
     */
    constructor(js_val: JSONArray) {
        for (i in 0 until js_val.length()) {
            if (!js_val.isNull(i)) {
                try {
                    when (i) {
                        0 -> county = js_val.getString(i)
                        1 -> name = js_val.getString(i)
                        2 -> address = js_val.getString(i)
                        3 -> description = js_val.getString(i)
                        4 -> phone = js_val.getString(i)
                        5 -> lat = js_val.getDouble(i)
                        6 -> lng = js_val.getDouble(i)
                        7 -> type = js_val.getString(i)
                        8 -> placeId = js_val.getString(i)
                        9 -> if (!js_val.isNull(i) && "" != js_val.getString(i)) {
                            accessLat = js_val.getDouble(i)
                        }
                        10 -> if (!js_val.isNull(i) && "" != js_val.getString(i)) {
                            accessLng = js_val.getDouble(i)
                        }
                        else -> {
                        }
                    }
                } catch (ex: JSONException) {
                    Log.e("[JSON Array]", "Football pitch constructor", ex)
                }
            }
        }
    }

    constructor(source: Parcel) {
        county = source.readString()
        name = source.readString()
        address = source.readString()
        description = source.readString()
        phone = source.readString()
        lat = source.readDouble()
        lng = source.readDouble()
        type = source.readString()
        placeId = source.readString()
        accessLat = source.readDouble()
        accessLng = source.readDouble()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p: Parcel, flags: Int) {
        p.writeString(county)
        p.writeString(name)
        p.writeString(address)
        p.writeString(description)
        p.writeString(phone)
        p.writeDouble(lat)
        p.writeDouble(lng)
        p.writeString(type)
        p.writeString(placeId)
        p.writeDouble(accessLat)
        p.writeDouble(accessLng)
    }

    companion object {
        val CREATOR: Parcelable.Creator<Pitch?> =
            object : Parcelable.Creator<Pitch?> {
                override fun createFromParcel(source: Parcel): Pitch? {
                    return Pitch(source)
                }

                override fun newArray(size: Int): Array<Pitch?> {
                    return arrayOfNulls(size)
                }
            }
    }
}