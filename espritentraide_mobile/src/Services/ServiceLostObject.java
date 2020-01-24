/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.LostObject;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.User;

/**
 *
 * @author Seif Bejaoui
 */
public class ServiceLostObject {

    User user;

    public ServiceLostObject() {
        user = new User();
    }

    public ArrayList<LostObject> search(String mot, Boolean annonce, Boolean demande, Date p) {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/search_mobile?mot=" + mot + "&annonce=" + annonce + "&demande=" + demande + "&date=" + new SimpleDateFormat("dd-MM-yyyy").format(p).toString());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    System.out.println(tasks.get("op"));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        LostObject task = new LostObject();
                        float id = Float.parseFloat(obj.get("id").toString());

                        System.out.println(obj.get("CreatedAt"));

                        task.setId((int) id);
                        task.setTitle(obj.get("title").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setType(obj.get("type").toString());

                        try {
                            task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                        } catch (ParseException ex) {
                            System.out.println("Exception:" + ex);
                        }
                        task.setUsername(obj.get("username").toString());
                        task.setUsermail(obj.get("usermail").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getAllObject() {

        System.out.println("5555555555555555555555555555555555555");
        System.out.println(user.getId());
        
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_op_mobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
           
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        LostObject task = new LostObject();
                        float id = Float.parseFloat(obj.get("id").toString());

                        System.out.println(obj.get("CreatedAt"));

                        task.setId((int) id);
                        task.setTitle(obj.get("title").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setType(obj.get("type").toString());

                        try {
                            task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                        } catch (ParseException ex) {
                            System.out.println("Exception:" + ex);
                        }
                        task.setUsername(obj.get("username").toString());
                        task.setUsermail(obj.get("usermail").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getFoundObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_annonce_mobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        LostObject task = new LostObject();
                        float id = Float.parseFloat(obj.get("id").toString());

                        System.out.println(obj.get("CreatedAt"));

                        task.setId((int) id);
                        task.setTitle(obj.get("title").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setType(obj.get("type").toString());

                        try {
                            task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                        } catch (ParseException ex) {
                            System.out.println("Exception:" + ex);
                        }
                        task.setUsername(obj.get("username").toString());
                        task.setUsermail(obj.get("usermail").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getLostObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_demande_mobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        LostObject task = new LostObject();
                        float id = Float.parseFloat(obj.get("id").toString());

                        System.out.println(obj.get("CreatedAt"));

                        task.setId((int) id);
                        task.setTitle(obj.get("title").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setType(obj.get("type").toString());

                        try {
                            task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                        } catch (ParseException ex) {
                            System.out.println("Exception:" + ex);
                        }
                        task.setUsername(obj.get("username").toString());
                        task.setUsermail(obj.get("usermail").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getMyAdsObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_myads_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        LostObject task = new LostObject();
                        float id = Float.parseFloat(obj.get("id").toString());

                        System.out.println(obj.get("CreatedAt"));

                        task.setId((int) id);
                        task.setTitle(obj.get("title").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setType(obj.get("type").toString());

                        try {
                            task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                        } catch (ParseException ex) {
                            System.out.println("Exception:" + ex);
                        }
                        task.setUsername(obj.get("username").toString());
                        task.setUsermail(obj.get("usermail").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getMyDmdObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_mydmd_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        LostObject task = new LostObject();
                        float id = Float.parseFloat(obj.get("id").toString());

                        System.out.println(obj.get("CreatedAt"));

                        task.setId((int) id);
                        task.setTitle(obj.get("title").toString());
                        task.setDescription(obj.get("description").toString());
                        task.setType(obj.get("type").toString());

                        try {
                            task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                        } catch (ParseException ex) {
                            System.out.println("Exception:" + ex);
                        }
                        task.setUsername(obj.get("username").toString());
                        task.setUsermail(obj.get("usermail").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public void deleteAds(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/cancel_ads?id=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    System.out.println(tasks.get("error"));

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public void ModifAds(int id, String title, String desc) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/update_adsById?id=" + id + "&title=" + title + "&description=" + desc);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    System.out.println(tasks.get("error"));

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public void AddAds(String title, String desc, String type) {
        int id = user.getId();
        ConnectionRequest con = new ConnectionRequest();
        if (type.equals("annonce")) {
            con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/add_ads_OP_mobile?title=" + title + "&description=" + desc + "&user=" + id);
        } else {
            con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/add_dmd_OP_mobile?title=" + title + "&description=" + desc + "&user=" + id);
        }
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    System.out.println(tasks.get("error"));

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public ArrayList<LostObject> getMyPublishAdsObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_myads_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        if ((Boolean.parseBoolean(obj.get("cancel").toString()) == false) && (Boolean.parseBoolean(obj.get("enable").toString()) == true)) {
                            LostObject task = new LostObject();
                            float id = Float.parseFloat(obj.get("id").toString());

                            task.setId((int) id);
                            task.setTitle(obj.get("title").toString());
                            task.setDescription(obj.get("description").toString());
                            task.setType(obj.get("type").toString());

                            try {
                                task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                            } catch (ParseException ex) {
                                System.out.println("Exception:" + ex);
                            }
                            task.setUsername(obj.get("username").toString());
                            task.setUsermail(obj.get("usermail").toString());
                            listTasks.add(task);
                        }

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getMyWaitingAdsObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_myads_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        if ((Boolean.parseBoolean(obj.get("cancel").toString()) == false) && (Boolean.parseBoolean(obj.get("enable").toString()) == false)) {
                            LostObject task = new LostObject();
                            float id = Float.parseFloat(obj.get("id").toString());

                            task.setId((int) id);
                            task.setTitle(obj.get("title").toString());
                            task.setDescription(obj.get("description").toString());
                            task.setType(obj.get("type").toString());

                            try {
                                task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                            } catch (ParseException ex) {
                                System.out.println("Exception:" + ex);
                            }
                            task.setUsername(obj.get("username").toString());
                            task.setUsermail(obj.get("usermail").toString());
                            listTasks.add(task);
                        }

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getMyCancelAdsObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_myads_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        if ((Boolean.parseBoolean(obj.get("cancel").toString()) == true) && (Boolean.parseBoolean(obj.get("enable").toString()) == false)) {
                            LostObject task = new LostObject();
                            float id = Float.parseFloat(obj.get("id").toString());

                            task.setId((int) id);
                            task.setTitle(obj.get("title").toString());
                            task.setDescription(obj.get("description").toString());
                            task.setType(obj.get("type").toString());

                            try {
                                task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                            } catch (ParseException ex) {
                                System.out.println("Exception:" + ex);
                            }
                            task.setUsername(obj.get("username").toString());
                            task.setUsermail(obj.get("usermail").toString());
                            listTasks.add(task);
                        }

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getMyPublishDmdObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_mydmd_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        if ((Boolean.parseBoolean(obj.get("cancel").toString()) == false) && (Boolean.parseBoolean(obj.get("enable").toString()) == true)) {
                            LostObject task = new LostObject();
                            float id = Float.parseFloat(obj.get("id").toString());

                            task.setId((int) id);
                            task.setTitle(obj.get("title").toString());
                            task.setDescription(obj.get("description").toString());
                            task.setType(obj.get("type").toString());

                            try {
                                task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                            } catch (ParseException ex) {
                                System.out.println("Exception:" + ex);
                            }
                            task.setUsername(obj.get("username").toString());
                            task.setUsermail(obj.get("usermail").toString());
                            listTasks.add(task);
                        }

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getMyWaitingDmdObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_mydmd_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        if ((Boolean.parseBoolean(obj.get("cancel").toString()) == false) && (Boolean.parseBoolean(obj.get("enable").toString()) == false)) {
                            LostObject task = new LostObject();
                            float id = Float.parseFloat(obj.get("id").toString());

                            task.setId((int) id);
                            task.setTitle(obj.get("title").toString());
                            task.setDescription(obj.get("description").toString());
                            task.setType(obj.get("type").toString());

                            try {
                                task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                            } catch (ParseException ex) {
                                System.out.println("Exception:" + ex);
                            }
                            task.setUsername(obj.get("username").toString());
                            task.setUsermail(obj.get("usermail").toString());
                            listTasks.add(task);
                        }

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<LostObject> getMyCancelDmdObject() {
        ArrayList<LostObject> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/EspritEntraide/web/app_dev.php/get_mydmd_mobile/"+user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));

                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("op");
                    for (Map<String, Object> obj : list) {
                        if ((Boolean.parseBoolean(obj.get("cancel").toString()) == true) && (Boolean.parseBoolean(obj.get("enable").toString()) == false)) {
                            LostObject task = new LostObject();
                            float id = Float.parseFloat(obj.get("id").toString());

                            task.setId((int) id);
                            task.setTitle(obj.get("title").toString());
                            task.setDescription(obj.get("description").toString());
                            task.setType(obj.get("type").toString());

                            try {
                                task.setCreated_at(new SimpleDateFormat("yyyy-dd-mm HH:mm:ss").parse(obj.get("CreatedAt").toString()));

                            } catch (ParseException ex) {
                                System.out.println("Exception:" + ex);
                            }
                            task.setUsername(obj.get("username").toString());
                            task.setUsermail(obj.get("usermail").toString());
                            listTasks.add(task);
                        }

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

}
