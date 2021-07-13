package club.deltapvp.deltacore.api.utilities.downloader;

public interface DownloadCallback {
    void onSuccess();

    void onError(int var1);

    void onExist();
}
