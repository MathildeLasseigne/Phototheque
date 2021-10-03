package Model;

import Widget.PhotoComponent;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PhotoDataBase {


    private static ArrayList<PhotoData> photoList = new ArrayList<>();

    /**
     * Manage all photos added
     */
    public PhotoDataBase(){

    }

    /**
     * Try to register a photo data, and check if the photo is already registered
     * @param photoData the new photo to register
     * @return the original photo if not yet registered, the old photo data if registered
     */
    public static PhotoData registerNewPhotoData(PhotoData photoData){
        for(PhotoData phdata : photoList){
            if(phdata.getPath() == photoData.getPath()){
                return phdata;
            }
        }
        photoList.add(photoData);
        return photoData;
    }


    public static class PhotoData {

        private String path;

        private BufferedImage originalPhoto;

        private PhotoComponent photoComponent = null;

        /**
         * Register information on a photo
         * @param path
         */
        public PhotoData(String path, BufferedImage originalPhoto) {
            this.path = path;
            this.originalPhoto = originalPhoto;
        }

        /**
         * Register photo component into this
         * @param photoComponent
         */
        public void registerPhotoComponent(PhotoComponent photoComponent){
            this.photoComponent = photoComponent;
        }

        /**
         * Return the path of the photo
         * @return
         */
        public String getPath() {
            return path;
        }

        /**
         * Return the photo component created for this photo
         * @return
         */
        public PhotoComponent getPhotoComponent() {
            return photoComponent;
        }

        /**
         * Return the original photo
         * @return
         */
        public BufferedImage getOriginalPhoto() {
            return originalPhoto;
        }
    }
}
