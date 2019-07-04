package devliving.online.cvscanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import org.opencv.core.Point;

import devliving.online.cvscanner.util.Util;

public class DocumentData implements Parcelable {
    public final static int V_FILTER_TYPE_COLOR = 0;
    public final static int V_FILTER_TYPE_GRAYSCALE = 1;
    public final static int V_FILTER_TYPE_BLACK_WHITE = 2;
    public final static int V_FILTER_TYPE_PHOTO = 3;

    private Bitmap mOriginalImage;
    private String mOriginalImagePath;
    private int mRotation;
    private Point[] mPoints;
    private int mFilterType;
    private String mImagePath;

    public static final Parcelable.Creator<DocumentData> CREATOR = new Parcelable.Creator<DocumentData>() {
        @Override
        public DocumentData createFromParcel(Parcel source) {
            return new DocumentData(source);
        }

        @Override
        public DocumentData[] newArray(int size) {
            return new DocumentData[size];
        }
    };

    DocumentData(Parcel parcel) {
        mOriginalImagePath = parcel.readString();
        mRotation = parcel.readInt();
        int pointLength = parcel.readInt();
        mPoints = new Point[pointLength];
        for (int n = 0; n < pointLength; n++)
            mPoints[n] = new Point(parcel.readDouble(), parcel.readDouble());
        mFilterType = parcel.readInt();
        mImagePath = parcel.readString();
    }

    public DocumentData(Context context, Document document, int filterType) {
        this(context, document.getImage().getBitmap(), document.getImage().getMetadata().getRotation(), document.detectedQuad.points, filterType);
    }

    public DocumentData(Context context, Bitmap originalImage, int filterType) {
        this(context, originalImage, 0, new Point[0], filterType);
    }

    private DocumentData(Context context, Bitmap originalImage, int rotation, Point[] points, int filterType) {
        mOriginalImage = originalImage;
        mOriginalImagePath = Util.saveImage(context, "IMG_CVScanner_" + System.currentTimeMillis(), originalImage, false);
        mRotation = 0;
        mPoints = new Point[0];
        mFilterType = filterType;
        mImagePath = null;
    }

    public void setRotation(int rotation) {
        mRotation = rotation;
    }

    public int getRotation() {
        return mRotation;
    }

    public void setFilterType(int filterType) {
        mFilterType = filterType;
    }

    public String getImagePath() {
        return mImagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOriginalImagePath);
        dest.writeInt(mRotation);
        dest.writeInt(mPoints.length);
        for (int n = 0; n < mPoints.length; n++) {
            dest.writeDouble(mPoints[n].x);
            dest.writeDouble(mPoints[n].y);
        }
        dest.writeInt(mFilterType);
        dest.writeString(mImagePath);
    }
}
