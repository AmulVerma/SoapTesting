package com.example.android.soaptesting;



//////////////////////////////////////////////////////////////////////////////////////////
/*
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONArray;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.LocalSocketAddress;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;



public class MainActivity extends Activity {
    boolean timeoutexcep=false,httpexcep=false,genexcep=false;
    private static final String TAG_NAME = "status";
    private static final String TAG_ID = "Description";
    String Name,Id,Place,Phone;
    Button button;
    TextView text;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        text=(TextView) findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 new GRPcomplaints().execute();
                //    GetChallanInfoInfoAsyncTask getChallanInfoInfoAsyncTask = new GetChallanInfoInfoAsyncTask();
                //  getChallanInfoInfoAsyncTask.execute(textBox.getText().toString());

            }
        });
    }

    class GRPcomplaints extends AsyncTask implements paypost {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected  String doInBackground(Object[] objects) {
            final String URL = "http://grphelpapp.in/supervisorwebservice.asmx?WSDL";
            final String SOAP_ACTION = "http://grphelpapp.in/GetGRPComplaint";
            final String METHOD_NAME = "GetGRPComplaint";
            final String NAMESPACE = "http://grphelpapp.in/";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("status", "Action Completed");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.debug = true;
            //Object ret=null;
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapObject response=(SoapObject)envelope.bodyIn;
                String Checkresponse= response.getProperty(0).toString();
                // SoapObject response = (SoapObject) envelope.getResponse();
                  //ret=response.toString();
                // String responseJSON=response.toString();
                //JSONArray jarray =new JSONArray(responseJSON);
                //  Name=jarray.getJSONObject(0).getString(TAG_NAME);
                System.out.println(Checkresponse);

                //System.out.println(responseJSON);

            } catch (SocketTimeoutException e) {

                timeoutexcep = true;

                e.printStackTrace();

            } catch (UnknownHostException e) {

                httpexcep = true;

                e.printStackTrace();

            } catch (Exception e) {

                genexcep = true;

                e.printStackTrace();

            }
        return null;
        }
        @Override
        public void onPostExecute(String S) {

            if (this.dialog.isShowing()) {

                this.dialog.dismiss();

            }
            if(timeoutexcep){

                Toast.makeText(MainActivity.this, "Unable to connect to server, Please try again later",Toast.LENGTH_LONG).show();

            }
            else if(httpexcep){

                Toast.makeText(MainActivity.this, "Please check your Internet connection",Toast.LENGTH_LONG).show();

            }

            else if(genexcep){

                Toast.makeText(MainActivity.this, "Please try later",Toast.LENGTH_LONG).show();

            }
            else{
               text.setText(S);
            }
            timeoutexcep=false;httpexcep=false;genexcep=false;
        }

    }


}
*/

///////////////////////////////////////////////////////////////////////////////////////////
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.soaptesting.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText textBox;
    Button button;
    TextView text;

    private final String URL = "http://grphelpapp.in/supervisorwebservice.asmx?WSDL";
    private static final String SOAP_ACTION = "http://grphelpapp.in/GetGRPComplaint";
    private static final String METHOD_NAME = "GetGRPComplaint/";

    private static final String NAMESPACE = "http://grphelpapp.in/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // new CallWebService().execute();

                //GetGRPComAsyncTask getGRPComAsyncTask = new GetGRPComAsyncTask();
                //getGRPComAsyncTask.execute("","Action Completed","","","");

                   GetChallanInfoInfoAsyncTask getChallanInfoInfoAsyncTask = new GetChallanInfoInfoAsyncTask();
                 getChallanInfoInfoAsyncTask.execute("","Action Completed","","","");

            }
        });

    }
    public void Recycler(View view) {
        Intent intent = new Intent(this, RecyclerComplaints.class);
        startActivity(intent);
    }

    class CallWebService extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {

            text.setText("Soap output " + s);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";

            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
            // params[0]=textBox.getText().toString();
            //PropertyInfo propertyInfo = new PropertyInfo();
            //propertyInfo.setName("GetGRPComplaint");
            //propertyInfo.setValue(params[0]);
            //propertyInfo.setType(String.class);
            //soapObject.addProperty(propertyInfo);
            soapObject.addProperty("status", "Action Completed");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);
            //adding new fields
            Log.v("checking", "running" + envelope);
            envelope.dotNet = true;

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            Object Response = null;
            try {
                httpTransportSE.call(SOAP_ACTION, envelope);
                //SoapPrimitive soapPrimitive = (SoapPrimitive)envelope.getResponse();
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

                //Response=envelope.getResponse();
                result = soapPrimitive.toString();
                Log.d("checking", soapPrimitive.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }
    //***********************************************************************************************************************






    ProgressDialog pg;

    public class GetChallanInfoInfoAsyncTask extends AsyncTask<String, String, String> {
        String UserRating = "";

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pg = new ProgressDialog(MainActivity.this);
            pg.show();
            pg.setCancelable(false);
            pg.setMessage("Please Wait...");
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            String stateid = params[0];
            String status = params[1];
            String category = params[2];
            String fromdate = params[3];
            String todate = params[4];


            Log.e("ID", status);
            return challanInfoFromServer(stateid,status,category,fromdate,todate);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (pg != null) {
                try {
                    pg.dismiss();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            if (!result.toString().trim().contains("False")) {

                try {
                    JSONObject object = new JSONObject(result);
                    String keystatus = object.getString("Result");

                    if (keystatus.equalsIgnoreCase("True")) {
                        //parseChallanInfoResponse(result);
                        setResult(RESULT_OK, null);
                    } else if (keystatus.equalsIgnoreCase("False")) {

                        Toast.makeText(getApplicationContext(), "Please try again later!", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //  finish();
            }
        }

        public String challanInfoFromServer(String stateid, String status, String category, String fromdate, String todate ) {

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

//            PropertyInfo Complaints = new PropertyInfo();
//            Complaints.setName("GetGRPComplaint");  // earlier GRPId
//           // Complaints.setValue(status);
//           // request.addProperty(Complaints);
//            request.addProperty("status",params[1]);
//            request.addProperty("stateid",params[0]);
//            request.addProperty("fromdate",params[3]);
//            request.addProperty("todate",params[4]);
//            request.addProperty("category",params[2]);


            PropertyInfo stateID = new PropertyInfo();
            stateID.setName("stateid");
            stateID.setValue(stateid);
            request.addProperty(stateID);


            PropertyInfo STATUS = new PropertyInfo();
            STATUS.setName("status");
            STATUS.setValue(status);
            request.addProperty(STATUS);

            PropertyInfo CATEGORY = new PropertyInfo();
            CATEGORY.setName("category");
            CATEGORY.setValue(category);
            request.addProperty(CATEGORY);


            PropertyInfo fromDATE = new PropertyInfo();
            fromDATE.setName("fromdate");
            fromDATE.setValue(fromdate);
            request.addProperty(fromDATE);


            PropertyInfo toDATE = new PropertyInfo();
            toDATE.setName("todate");
            toDATE.setValue(todate);
            request.addProperty(toDATE);




            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                // LoggerUtil.e("Response","IN TRY");
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                String res = response.toString();
                Log.e("Response", res + "");
                return res;
            } catch (IOException e) {
                Log.e("IOException", e.toString());

            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("Exception", e.toString());
            }
            return "[{\"result\":\"No Record Found\"}]";
        }
    }



 //Call API +++++++++++++++++++++++++++++++

ProgressDialog mDialog;



    public class GetGRPComAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            //mDialog.show();
            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.show();
            mDialog.setCancelable(false);
            mDialog.setMessage("Please Wait...");
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String stateid = params[0];
            String status = params[1];
            String category = params[2];
            String fromdate = params[3];
            String todate = params[4];

            Log.e("status", status);

            return CallApiForGetDonatedServer(stateid, status, category, fromdate, todate);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (mDialog != null) {
                try {
                    mDialog.dismiss();
                } catch (Exception e) {
                    // TODO: handle exception
                }


                // Log.e("Organization resp from server", result);

                if (!result.toString().trim().contains("fail")) {

                    //  parseDonatedListResponse(result);

                    try {

                        // Log.e("organization  list size>>>>>>>>", DonatedFoodDtoArrayList.size() + "");
                        //  Log.e("lat Long list size>>>>>>", LatLongListForAddress.size() + "");
                        //  Log.e("urls list size>>>>>>>", urls.size() + "");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        }


        @SuppressLint("LongLogTag")
        public String CallApiForGetDonatedServer(String stateid, String status, String category, String fromdate, String todate) {

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


            PropertyInfo stateID = new PropertyInfo();
            stateID.setName("stateid");
            stateID.setValue(stateid);
            request.addProperty(stateID);


            PropertyInfo STATUS = new PropertyInfo();
            STATUS.setName("status");
            STATUS.setValue(status);
            request.addProperty(STATUS);

            PropertyInfo CATEGORY = new PropertyInfo();
            CATEGORY.setName("category");
            CATEGORY.setValue(category);
            request.addProperty(CATEGORY);


            PropertyInfo fromDATE = new PropertyInfo();
            fromDATE.setName("fromdate");
            fromDATE.setValue(fromdate);
            request.addProperty(fromDATE);


            PropertyInfo toDATE = new PropertyInfo();
            toDATE.setName("todate");
            toDATE.setValue(todate);
            request.addProperty(toDATE);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                //LoggerUtil.e("Response","IN TRY");
                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                String res = response.toString();
                Log.e("Response>>>>>>>>>>>>>>>>>>>>", res + "");

                return res;
            } catch (IOException e) {
                Log.e("IOException", e.toString());

            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                Log.e("Exception", e.toString());
            }
            return "{\"Result\":\"No Record Found\"[{\"Result\":\"No Record Found\"}]}";
        }

        Handler handler = new Handler() {

            public void handleMessage(android.os.Message msg) {
                if (msg.what == 0) {
                    Toast.makeText(MainActivity.this, "No Record Found!", Toast.LENGTH_LONG).show();
                }
                if (msg.what == 1) {
                    Toast.makeText(MainActivity.this, "Please try again later!", Toast.LENGTH_LONG).show();
                }
            }

        };

    }



}
    //parse news response and get user dto
//change this parsing code according to server response
   /* public void parseChallanInfoResponse(String resp) {
        try {

            JSONObject object = new JSONObject(resp);
            String keystatus = object.getString("Result");

            if (keystatus.equalsIgnoreCase("True")) {

                JSONArray array = object.getJSONArray("GRPComplaint");
                ChallanInfoDtoArrayList = new Gson().fromJson(array.toString(), new TypeToken<List<DTO>>() {
                }.getType());

                JSONArray arryAfterConvert = new JSONArray(new Gson().toJson(ChallanInfoDtoArrayList));
                if (ChallanInfoDtoArrayList.size() > 0) {

                    for (int i = 0; i < ChallanInfoDtoArrayList.size(); i++) {

                    }
                }
                challanInfoAdapter = new ChallanInfoAdapter(ChallanInfoDtoArrayList, ChallanInfoRecyclerViewActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ChallanInfoRecyclerViewActivity.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.addItemDecoration(new DividerItemDecoration(ChallanInfoRecyclerViewActivity.this, LinearLayoutManager.VERTICAL));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(challanInfoAdapter);
                challanInfoAdapter.notifyDataSetChanged();


            } else if (keystatus.equalsIgnoreCase("False")) {

                handler.sendEmptyMessage(0);

            } else if (keystatus.equalsIgnoreCase("No Record Found")) {
                handler.sendEmptyMessage(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                Toast.makeText(MainActivity.this, "No Record Found!", Toast.LENGTH_LONG).show();
            }
            if (msg.what == 1) {
                Toast.makeText(MainActivity.this, "Please try again later!", Toast.LENGTH_LONG).show();
            }
        }

    };
*/








