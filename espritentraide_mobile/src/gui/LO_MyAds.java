/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.LostObject;
import Services.ServiceLostObject;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import java.util.ArrayList;

/**
 *
 * @author Seif Bejaoui
 */
public class LO_MyAds {

    Form form, f2, f3, f4;
    SpanLabel lb;
    private String str;
    private ArrayList<LostObject> list;
    private ArrayList<LostObject> list2;
     private ArrayList<LostObject> list3;
      private ArrayList<LostObject> list4;
    Resources theme = UIManager.initFirstTheme("/theme");

    public LO_MyAds() {

        form = new Form("Mes annonces", BoxLayout.y());

        form.getToolbar().addCommandToSideMenu("Recherche", null, e -> {
            LO_search search = new LO_search();
            search.getForm().show();
        });

        form.getToolbar().addCommandToSideMenu("Toutes les annonces", null, e -> {
            LO_ALLObject allObject = new LO_ALLObject();
            allObject.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Les objets perdus", null, e -> {
            LO_LostObject lostObject = new LO_LostObject();
            lostObject.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Les objets trouves", null, e -> {
            LO_FoundObject foundObject = new LO_FoundObject();
            foundObject.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Mes annonces", null, e -> {
            this.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Mes demandes", null, e -> {
            LO_MyDmd myDmd = new LO_MyDmd();
            myDmd.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Ajouter une annonce", null, e -> {
            LO_AddAds addAds = new LO_AddAds();
            addAds.getForm().show();
        });
        form.getToolbar().addCommandToSideMenu("Ajouter une demande", null, e -> {
            LO_AddDmd addDmd = new LO_AddDmd();
            addDmd.getForm().show();
        });
        ServiceLostObject ServiceLO = new ServiceLostObject();
        form.getToolbar().addCommandToOverflowMenu("Mes annonces publiés", null, e3 -> {
            f4 = new Form("Mes annonces publiés", BoxLayout.y());
            list2 = ServiceLO.getMyPublishAdsObject();

            if (list2.size() == 0) {
                Container c1 = new Container(new FlowLayout(Component.CENTER));
                Label err = new Label("Pas des annonces publiées");
                c1.add(err);
                f4.add(c1);
            }
            for (LostObject t : list2) {
                Container c1 = new Container(BoxLayout.x());
                Container c2 = new Container(BoxLayout.y());
                //ImageViewer img = new ImageViewer(theme.getImage(t.getImage()));
                ImageViewer img = new ImageViewer(theme.getImage("round.png").fill(75, 75));
                Label l2 = new Label(t.getTitle());
                Label l1 = new Label(t.getDescription());
                c2.add(l2).add(l1);
                c1.add(img).add(c2);
                c1.getStyle().setBorder(Border.createLineBorder(1));
                f4.add(c1);
                f4.show();
                l1.addPointerPressedListener(e -> {

                    EncodedImage enc = EncodedImage.createFromImage(theme.getImage("round.png").fill(150, 150), false);
                    Image image = URLImage.createToStorage(enc, "http://localhost/revision/img.png", "http://localhost/revision/img.png");
                    ImageViewer img1 = new ImageViewer(image);

                    Label type = new Label("**" + t.getType() + "**");
                    Label title = new Label("Title : " + t.getTitle());
                    Label desc = new Label("Description : " + t.getDescription());
                    Label date = new Label("Crée le : " + String.valueOf(t.getCreated_at()));
                    Label date1 = new Label("username le : " + t.getUsername());
                    f2 = new Form("Details annonce", BoxLayout.y());
                    f2.add(img1).add(type).add(title).add(desc).add(date).add(date1);

                    f2.getToolbar().addCommandToOverflowMenu("Modifier", null, e6 -> {
                        f3 = new Form("Modfier annonce", BoxLayout.y());
                        Label f3title = new Label("Titre :");
                        TextField txttitle = new TextField("", "Titre", 20, TextField.ANY);
                        txttitle.setText(t.getTitle());
                        Label f3description = new Label("Description :");
                        TextArea txtdesc = new TextArea(5, 20);
                        txtdesc.setText(t.getDescription());
                        Button modifier = new Button("Modifier");
                        Button annuler = new Button("Annuler");
                        f3.add(f3title).add(txttitle).add(f3description).add(txtdesc).add(modifier).add(annuler);
                        Validator v = new Validator();
                        v.addConstraint(txttitle, new LengthConstraint(2));
                        v.addConstraint(txtdesc, new LengthConstraint(2));
                        v.addSubmitButtons(modifier);
                        modifier.addActionListener(m -> {
                            ServiceLO.ModifAds(t.getId(), txttitle.getText(), txtdesc.getText());
                            Dialog.show("Modification effectué", "Votre annonce a été modifié avec succees", "ok", null);
                            LO_MyAds myads = new LO_MyAds();
                            myads.getForm().show();
                        });
                        annuler.addActionListener(m -> {
                            this.getForm().show();
                        });

                        f3.show();

                    });
                    f2.getToolbar().addCommandToOverflowMenu("Supprimer", null, e6 -> {
                        ServiceLO.deleteAds(t.getId());
                        Dialog.show("Suppression effectué", "Votre annonce a été supprimé avec succees", "ok", null);
                        LO_MyAds myads = new LO_MyAds();
                        myads.getForm().show();

                    });
                    f2.getToolbar().addCommandToOverflowMenu("Back", null, e6 -> {
                        this.getForm().show();
                    });
                    f2.show();
                });
                c1.setLeadComponent(l1);

            }

            f4.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
                this.getForm().show();
            });
            f4.show();
        });
        form.getToolbar().addCommandToOverflowMenu("Mes annonces en attentes", null, e3 -> {
            f4 = new Form("Mes annonces en attentes", BoxLayout.y());
            list3 = ServiceLO.getMyWaitingAdsObject();

            if (list3.size() == 0) {
                Container c1 = new Container(new FlowLayout(Component.CENTER));
                Label err = new Label("Pas des annonces en attentes");
                c1.add(err);
                f4.add(c1);
            }
            for (LostObject t : list3) {
                Container c1 = new Container(BoxLayout.x());
                Container c2 = new Container(BoxLayout.y());
                //ImageViewer img = new ImageViewer(theme.getImage(t.getImage()));
                ImageViewer img = new ImageViewer(theme.getImage("round.png").fill(75, 75));
                Label l2 = new Label(t.getTitle());
                Label l1 = new Label(t.getDescription());
                c2.add(l2).add(l1);
                c1.add(img).add(c2);
                c1.getStyle().setBorder(Border.createLineBorder(1));
                f4.add(c1);
                f4.show();
                l1.addPointerPressedListener(e -> {

                    EncodedImage enc = EncodedImage.createFromImage(theme.getImage("round.png").fill(150, 150), false);
                    Image image = URLImage.createToStorage(enc, "http://localhost/revision/img.png", "http://localhost/revision/img.png");
                    ImageViewer img1 = new ImageViewer(image);

                    Label type = new Label("**" + t.getType() + "**");
                    Label title = new Label("Title : " + t.getTitle());
                    Label desc = new Label("Description : " + t.getDescription());
                    Label date = new Label("Crée le : " + String.valueOf(t.getCreated_at()));
                    Label date1 = new Label("username le : " + t.getUsername());
                    f2 = new Form("Details annonce", BoxLayout.y());
                    f2.add(img1).add(type).add(title).add(desc).add(date).add(date1);

                    f2.getToolbar().addCommandToOverflowMenu("Modifier", null, e6 -> {
                        f3 = new Form("Modfier annonce", BoxLayout.y());
                        Label f3title = new Label("Titre :");
                        TextField txttitle = new TextField("", "Titre", 20, TextField.ANY);
                        txttitle.setText(t.getTitle());
                        Label f3description = new Label("Description :");
                        TextArea txtdesc = new TextArea(5, 20);
                        txtdesc.setText(t.getDescription());
                        Button modifier = new Button("Modifier");
                        Button annuler = new Button("Annuler");
                        f3.add(f3title).add(txttitle).add(f3description).add(txtdesc).add(modifier).add(annuler);
                        Validator v = new Validator();
                        v.addConstraint(txttitle, new LengthConstraint(2));
                        v.addConstraint(txtdesc, new LengthConstraint(2));
                        v.addSubmitButtons(modifier);
                        modifier.addActionListener(m -> {
                            ServiceLO.ModifAds(t.getId(), txttitle.getText(), txtdesc.getText());
                            Dialog.show("Modification effectué", "Votre annonce a été modifié avec succees", "ok", null);
                            LO_MyAds myads = new LO_MyAds();
                            myads.getForm().show();
                        });
                        annuler.addActionListener(m -> {
                            this.getForm().show();
                        });

                        f3.show();

                    });
                    f2.getToolbar().addCommandToOverflowMenu("Supprimer", null, e6 -> {
                        ServiceLO.deleteAds(t.getId());
                        Dialog.show("Suppression effectué", "Votre annonce a été supprimé avec succees", "ok", null);
                        LO_MyAds myads = new LO_MyAds();
                        myads.getForm().show();

                    });
                    f2.getToolbar().addCommandToOverflowMenu("Back", null, e6 -> {
                        this.getForm().show();
                    });
                    f2.show();
                });
                c1.setLeadComponent(l1);

            }

            f4.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
                this.getForm().show();
            });
            f4.show();
        });
        form.getToolbar().addCommandToOverflowMenu("Mes annonces annulées", null, e3 -> {
            f4 = new Form("Mes annonces annulées", BoxLayout.y());
            list4 = ServiceLO.getMyCancelAdsObject();

            if (list4.size() == 0) {
                Container c1 = new Container(new FlowLayout(Component.CENTER));
                Label err = new Label("Pas des annonces annulées");
                c1.add(err);
                f4.add(c1);
            }
            for (LostObject t : list4) {
                Container c1 = new Container(BoxLayout.x());
                Container c2 = new Container(BoxLayout.y());
                //ImageViewer img = new ImageViewer(theme.getImage(t.getImage()));
                ImageViewer img = new ImageViewer(theme.getImage("round.png").fill(75, 75));
                Label l2 = new Label(t.getTitle());
                Label l1 = new Label(t.getDescription());
                c2.add(l2).add(l1);
                c1.add(img).add(c2);
                c1.getStyle().setBorder(Border.createLineBorder(1));
                f4.add(c1);
                f4.show();
                l1.addPointerPressedListener(e -> {

                    EncodedImage enc = EncodedImage.createFromImage(theme.getImage("round.png").fill(150, 150), false);
                    Image image = URLImage.createToStorage(enc, "http://localhost/revision/img.png", "http://localhost/revision/img.png");
                    ImageViewer img1 = new ImageViewer(image);

                    Label type = new Label("**" + t.getType() + "**");
                    Label title = new Label("Title : " + t.getTitle());
                    Label desc = new Label("Description : " + t.getDescription());
                    Label date = new Label("Crée le : " + String.valueOf(t.getCreated_at()));
                    Label date1 = new Label("username le : " + t.getUsername());
                    f2 = new Form("Details annonce", BoxLayout.y());
                    f2.add(img1).add(type).add(title).add(desc).add(date).add(date1);

                    f2.getToolbar().addCommandToOverflowMenu("Modifier", null, e6 -> {
                        f3 = new Form("Modfier annonce", BoxLayout.y());
                        Label f3title = new Label("Titre :");
                        TextField txttitle = new TextField("", "Titre", 20, TextField.ANY);
                        txttitle.setText(t.getTitle());
                        Label f3description = new Label("Description :");
                        TextArea txtdesc = new TextArea(5, 20);
                        txtdesc.setText(t.getDescription());
                        Button modifier = new Button("Modifier");
                        Button annuler = new Button("Annuler");
                        f3.add(f3title).add(txttitle).add(f3description).add(txtdesc).add(modifier).add(annuler);
                        Validator v = new Validator();
                        v.addConstraint(txttitle, new LengthConstraint(2));
                        v.addConstraint(txtdesc, new LengthConstraint(2));
                        v.addSubmitButtons(modifier);
                        modifier.addActionListener(m -> {
                            ServiceLO.ModifAds(t.getId(), txttitle.getText(), txtdesc.getText());
                            Dialog.show("Modification effectué", "Votre annonce a été modifié avec succees", "ok", null);
                            LO_MyAds myads = new LO_MyAds();
                            myads.getForm().show();
                        });
                        annuler.addActionListener(m -> {
                            this.getForm().show();
                        });

                        f3.show();

                    });
                    f2.getToolbar().addCommandToOverflowMenu("Supprimer", null, e6 -> {
                        ServiceLO.deleteAds(t.getId());
                        Dialog.show("Suppression effectué", "Votre annonce a été supprimé avec succees", "ok", null);
                        LO_MyAds myads = new LO_MyAds();
                        myads.getForm().show();

                    });
                    f2.getToolbar().addCommandToOverflowMenu("Back", null, e6 -> {
                        this.getForm().show();
                    });
                    f2.show();
                });
                c1.setLeadComponent(l1);

            }

            f4.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
                this.getForm().show();
            });
            f4.show();
        });

        list = ServiceLO.getMyAdsObject();
        if (list.size() == 0) {
            Container c1 = new Container(new FlowLayout(Component.CENTER));
            Label err = new Label("Pas de resultats");
            c1.add(err);
            form.add(c1);
        }
        for (LostObject t : list) {
            Container c1 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.y());
            //ImageViewer img = new ImageViewer(theme.getImage(t.getImage()));
            ImageViewer img = new ImageViewer(theme.getImage("round.png").fill(75, 75));
            Label l2 = new Label(t.getTitle());
            Label l1 = new Label(t.getDescription());
            c2.add(l2).add(l1);
            c1.add(img).add(c2);
            c1.getStyle().setBorder(Border.createLineBorder(1));
            form.add(c1);
            form.show();
            l1.addPointerPressedListener(e -> {

                EncodedImage enc = EncodedImage.createFromImage(theme.getImage("round.png").fill(150, 150), false);
                Image image = URLImage.createToStorage(enc, "http://localhost/revision/img.png", "http://localhost/revision/img.png");
                ImageViewer img1 = new ImageViewer(image);

                Label type = new Label("**" + t.getType() + "**");
                Label title = new Label("Title : " + t.getTitle());
                Label desc = new Label("Description : " + t.getDescription());
                Label date = new Label("Crée le : " + String.valueOf(t.getCreated_at()));
                Label date1 = new Label("username le : " + t.getUsername());
                f2 = new Form("Details annonce", BoxLayout.y());
                f2.add(img1).add(type).add(title).add(desc).add(date).add(date1);

                f2.getToolbar().addCommandToOverflowMenu("Modifier", null, e3 -> {
                    f3 = new Form("Modfier annonce", BoxLayout.y());
                    Label f3title = new Label("Titre :");
                    TextField txttitle = new TextField("", "Titre", 20, TextField.ANY);
                    txttitle.setText(t.getTitle());
                    Label f3description = new Label("Description :");
                    TextArea txtdesc = new TextArea(5, 20);
                    txtdesc.setText(t.getDescription());
                    Button modifier = new Button("Modifier");
                    Button annuler = new Button("Annuler");
                    f3.add(f3title).add(txttitle).add(f3description).add(txtdesc).add(modifier).add(annuler);
                    Validator v = new Validator();
                    v.addConstraint(txttitle, new LengthConstraint(2));
                    v.addConstraint(txtdesc, new LengthConstraint(2));
                    v.addSubmitButtons(modifier);
                    modifier.addActionListener(m -> {
                        ServiceLO.ModifAds(t.getId(), txttitle.getText(), txtdesc.getText());
                        Dialog.show("Modification effectué", "Votre annonce a été modifié avec succees", "ok", null);
                        LO_MyAds myads = new LO_MyAds();
                        myads.getForm().show();
                    });
                    annuler.addActionListener(m -> {
                        this.getForm().show();
                    });

                    f3.show();

                });
                f2.getToolbar().addCommandToOverflowMenu("Supprimer", null, e3 -> {
                    ServiceLO.deleteAds(t.getId());
                    Dialog.show("Suppression effectué", "Votre annonce a été supprimé avec succees", "ok", null);
                    LO_MyAds myads = new LO_MyAds();
                    myads.getForm().show();

                });
                f2.getToolbar().addCommandToOverflowMenu("Back", null, e3 -> {
                    this.getForm().show();
                });
                f2.show();
            });
            c1.setLeadComponent(l1);

        }

    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
