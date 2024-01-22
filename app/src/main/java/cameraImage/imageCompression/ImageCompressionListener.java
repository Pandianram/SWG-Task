package cameraImage.imageCompression;

public interface ImageCompressionListener {
    void onStart();
    void onCompressed(String filePath);
}
