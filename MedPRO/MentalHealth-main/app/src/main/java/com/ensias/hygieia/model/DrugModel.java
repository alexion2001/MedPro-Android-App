package com.ensias.hygieia.model;


        import android.os.Parcel;
        import android.os.Parcelable;

        import androidx.annotation.NonNull;

public class DrugModel implements Parcelable {

    private String name;
    private String keywords;

    public DrugModel(String name, String keywords) {
        this.name = name;
        this.keywords = keywords;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getKeywords() {
        return keywords;
    }


    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int i) {
        dest.writeStringArray(new String[] {this.name, this.keywords});
    }

    public DrugModel(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);

        this.name = data[0];
        this.keywords = data[1];

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel in) {
            return new DrugModel(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new DrugModel[size];
        }
    };
}