package pl.mbos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mateusz on 04.11.13.
 */
public class MyObject implements Parcelable {

    public static final String KEY = "MYOBJECT";

    public static final Parcelable.Creator<MyObject> CREATOR = new
            Parcelable.Creator<MyObject>() {
                public MyObject createFromParcel(Parcel in) {
                    return new MyObject(in);
                }

                public MyObject[] newArray(int size) {
                    return new MyObject[size];
                }
            };

    private String name;
    private int count;

    public MyObject() {
        name = "Student";
        count = 0;
    }

    public MyObject(String name, int count){
        this.name = name;
        this.count = count;
    }

    public MyObject(Parcel parcel) {
        this.name = parcel.readString();
        this.count = parcel.readInt();
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(count);
    }
}
