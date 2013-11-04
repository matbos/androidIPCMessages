package pl.mbos.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.widget.Toast;

import pl.mbos.MyObject;

/**
 * Created by Mateusz on 04.11.13.
 */
public class MessengerService extends Service {

    public static final int MSG_SAY_HELLO = 1;
    public static final int MSG_MY_OBJECT = 2;

    final Messenger messenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello! "+ android.os.Process.myPid(), Toast.LENGTH_SHORT).show();
                    break;
                case MSG_MY_OBJECT:
                    Bundle bundle = msg.getData();
                    bundle.setClassLoader(MyObject.class.getClassLoader());
                    MyObject mobj = (MyObject) bundle.get(MyObject.KEY);
                    if (mobj == null){
                        Toast.makeText(getApplicationContext(), "hello! mobj == null "+ android.os.Process.myPid(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "hello! "+mobj.getName()+ " " + mobj.getCount() + " is in process: " + android.os.Process.myPid(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
