

public class GraphActivity extends AppCompatActivity {

    private LineChart lineChart;
    RecordInfo[] recordInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        lineChart = (LineChart)findViewById(R.id.chart);
        recordInfo = DBhelper.getRecord(this, 1);

        List<Entry> pushUpEntry = new ArrayList<>();
        for(int i=0; i<pushUpEntry.length; i++){
            pushUpEntry.add(new Entry(recordInfo[i].date, Integer.parseInt(recordInfo[i].push_up)));
        }

        LineDataSet lineDataSet_pushUp = new LineDataSet(pushUpEntry, "팔굽혀펴기");
        lineDataSet_pushUp.setLineWidth(2);
        lineDataSet_pushUp.setCircleRadius(6);
        lineDataSet_pushUp.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setCircleColorHole(Color.BLUE);
        lineDataSet_pushUp.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setDrawCircleHole(true);
        lineDataSet_pushUp.setDrawCircles(true);
        lineDataSet_pushUp.setDrawValues(false);

        LineData lineData= new LineData(lineDataSet_pushUp);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("PUSH_UP");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();

    }

    public void onClickPushUp(View view) {
        
        List<Entry> pushUpEntry = new ArrayList<>();
        for(int i=0; i<pushUpEntry.length; i++){
            pushUpEntry.add(new Entry(recordInfo[i].date, Integer.parseInt(recordInfo[i].push_up)));
        }

        LineDataSet lineDataSet_pushUp = new LineDataSet(pushUpEntry, "팔굽혀펴기");
        lineDataSet_pushUp.setLineWidth(2);
        lineDataSet_pushUp.setCircleRadius(6);
        lineDataSet_pushUp.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setCircleColorHole(Color.BLUE);
        lineDataSet_pushUp.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_pushUp.setDrawCircleHole(true);
        lineDataSet_pushUp.setDrawCircles(true);
        lineDataSet_pushUp.setDrawValues(false);

        LineData lineData= new LineData(lineDataSet_pushUp);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("PUSH_UP");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }

    public void onClickSitUp(View view){

        List<Entry> sitUpEntry = new ArrayList<>();
        for(int i=0; i<SitUpEntry.length; i++){
            SitUpEntry.add(new Entry(recordInfo[i].date, Integer.parseInt(recordInfo[i].sit_up)));
        }

        LineDataSet lineDataSet_sitUp = new LineDataSet(sitUpEntry, "윗몸일으키기");
        lineDataSet_sitUp.setLineWidth(2);
        lineDataSet_sitUp.setCircleRadius(6);
        lineDataSet_sitUp.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_sitUp.setCircleColorHole(Color.BLUE);
        lineDataSet_sitUp.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet_sitUp.setDrawCircleHole(true);
        lineDataSet_sitUp.setDrawCircles(true);
        lineDataSet_sitUp.setDrawValues(false);
    
        LineData lineData = new LineData(lineDataSet_sitUp);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("SIT_UP");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
        
    }

    public void conClickRunning(View view){

        List<Entry> runningEntry = new ArrayList<>();
        for(int i=0; i<RunningEntry.length; i++){
            RunningEntry.add(new Entry(recordInfo[i].date, Integer.parseInt(recordInfo[i].running)));
        }

        LineDataSet lineDataSet_running = new LineDataSet(runningEntry, "3km 달리기");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(false);

        LineData lineData = new LineData(lineDataSet_running);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("Running");

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }


    public void onClickDummy(View view){
        init();
    }

    public void init(){
        for(int i=10; i<24; i++){
            RecordInfo recordinfo = new RecordInfo();
            recordinfo.setId(1);
            recordinfo.setPushup(67+i);
            recordinfo.setRunning(720+i);
            recordinfo.setSitup(80+i);
            //Date date = new Date(System.currentTimeMillis());
            //SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd");
            //String formatDate = sdfNow.format(date);
            String formatDate = "2020-08-"+i;
            recordinfo.setDate(formatDate);
            DBhelper.setPushUpRecord(this, recordinfo.id, recordinfo.push_up);
            DBhelper.setSitUpRecord(this, recordinfo.id, recordinfo.sit_up);
            DBhelper.setRunningRecord(this, recordinfo.id, recordinfo.running);
        }
    }
}