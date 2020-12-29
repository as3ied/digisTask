package net.senior.digistask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.skydoves.progressview.ProgressView;

import net.senior.digistask.api.ConnectionManager;

import net.senior.digistask.model.ColorModel;
import net.senior.digistask.model.Colors;
import net.senior.digistask.model.Model;
import net.senior.divistask.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {
    ProgressView rsrp, sinr, rsrq;
    Disposable disposable;
    LineChartView chart1,chart2,chart3;
    Model m;
    int temp;
    SimpleDateFormat sd;
    List<Float> rsrpValues;
    List<Float> rsrqValues;
    List<Float> sinrValues;
    List<String> times;
    List<AxisValue> xVal;
    private Line line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rsrp = findViewById(R.id.RSRP);
        sinr = findViewById(R.id.SINR);
        rsrq = findViewById(R.id.RSRQ);
        chart1 = findViewById(R.id.chart_1);
        chart2 = findViewById(R.id.chart_2);
        chart3 = findViewById(R.id.chart_3);
        rsrpValues = new ArrayList<>();
        rsrqValues = new ArrayList<>();
        sinrValues = new ArrayList<>();
        times = new ArrayList<>();
        xVal = new ArrayList<>();


        sd = new SimpleDateFormat("mm:ss");

        disposable = ConnectionManager.getInstance().
                getAll().toObservable().
                subscribeOn(Schedulers.io()).
                delay(2, TimeUnit.SECONDS).
                repeat().
                doOnError(throwable -> {
                    throwable.printStackTrace();
                }).subscribe(model -> {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String jsonFileString = null;
                    try {
                        jsonFileString = inputStreamToString(getAssets().open("legend.json"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Colors myModel = new Gson().fromJson(jsonFileString, Colors.class);
                    rsrpValues.add(model.getRSRP());
                    rsrqValues.add(model.getRSRQ());
                    sinrValues.add(model.getSINR());
                    if (temp < 10) {
                        times.add(sd.format(new Date()));
                        xVal.add(new AxisValue(temp).setLabel(times.get(temp)) );

                        setData(rsrpValues,chart1);
                        setData(rsrqValues,chart2);
                        setData(sinrValues,chart3);
                        temp++;

                    }

                    rsrq.getHighlightView().setColor(getColor(myModel.getRSRQ(), model.getRSRQ()));
                    rsrp.getHighlightView().setColor(getColor(myModel.getRSRP(), model.getRSRP()));
                    sinr.getHighlightView().setColor(getColor(myModel.getSINR(), model.getSINR()));


                    rsrp.setLabelText(model.getRSRP() + "");
                    rsrp.setProgress(Math.abs((int) model.getRSRP()));

                    sinr.setLabelText(model.getSINR() + "");
                    sinr.setProgress((int) Math.abs(model.getSINR()));

                    rsrq.setLabelText(model.getRSRQ() + "");
                    rsrq.setProgress((int) Math.abs(model.getRSRQ()));


                }
            });

        });
    }


    private void setData(List<Float> rsrpValues ,LineChartView chart) {



        List<Line> lines = new ArrayList();
        for (int i = 0; i < 1; ++i) {

            List<PointValue> values = new ArrayList();
            for (int j = 0; j < 10 && j < rsrpValues.size(); ++j) {
                values.add(new PointValue(j, rsrpValues.get(j)));

            }
            line = new Line(values).setColor(Color.parseColor("#FFCD41"));  //The color of the broken line (orange)

        }

            line.setShape(ValueShape.CIRCLE);
            line.setCubic(false);
            line.setFilled(false);
            line.setHasLabels(true);
            line.setHasLines(true);
            line.setHasPoints(true);
            lines.add(line);

        Axis axisX = new Axis();

        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.BLACK);
        axisX.setTextSize(10);
        axisX.setValues(xVal);
        axisX.setHasLines(true);

        Axis axisY = new Axis();

        axisY.setTextSize(10);
        axisY.setTextColor(Color.BLACK);
        axisY.setHasLines(true);
        LineChartData data = new LineChartData();

        data.setLines(lines);
        data.setAxisXBottom(axisX);

        data.setAxisYLeft(axisY);
        chart.setLineChartData(data);
        chart.setVisibility(View.VISIBLE);

    }


    public static int getColor( List<ColorModel> colors, double search) {

        for (int i = 0; i < colors.size(); i++) {
            double from ;
            double to ;
            if (colors.get(i).getFrom().equalsIgnoreCase("min")) {
                from = -1 * Double.MAX_VALUE;
            } else from = Double.parseDouble(colors.get(i).getFrom());

            if (colors.get(i).getTo().equalsIgnoreCase("max")) {
                to = Double.MAX_VALUE;
            } else to = Double.parseDouble(colors.get(i).getTo());
            if (search >= from && search < to) {
                return Color.parseColor(colors.get(i).getColor());
            }
        }
        throw new RuntimeException("NO color value found for:" + search + " in " + new Gson().toJson(colors));
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        disposable.dispose();
        Log.e("disp", "disposed");
    }
}

