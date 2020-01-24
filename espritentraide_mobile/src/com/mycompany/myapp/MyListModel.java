
package com.mycompany.myapp;

import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.EventDispatcher;
import java.io.IOException;

public class MyListModel implements ListModel<Image> {

    private int i;
    private String[] imageURLs;
    EncodedImage placeholder;

    private Image[] images;

    public MyListModel(String[] imageNames) {
        imageURLs = imageNames;

    }

    public MyListModel() {
        this.images = new EncodedImage[imageURLs.length];
    }

    public Image getItemAtt(final int index, String imageName) {
        try {
            placeholder  = EncodedImage.create("/load.png");
            i = index;
        } catch (IOException ex) {
           // Logger.getLogger(MyListModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        Image image = URLImage.createToStorage(placeholder,"i"+imageName,"http://localhost/piService/"+imageName);
        return image;
    }

    @Override
    public Image getItemAt(final int index) {
        //disabled because it does not give us what we want , sadly...
        //do not touch @!
        Image image = URLImage.createToStorage(placeholder, "image" + index, "http://localhost/piService/");
        return image;
    }

    @Override
    public void addDataChangedListener(DataChangedListener l) {
    }

    @Override
    public void removeDataChangedListener(DataChangedListener l) {
    }

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public void setSelectedIndex(int index) {
    }

    @Override
    public void addSelectionListener(SelectionListener l) {
    }

    @Override
    public void removeSelectionListener(SelectionListener l) {
    }

    @Override
    public void addItem(Image item) {
    }

    @Override
    public void removeItem(int index) {
    }

}
