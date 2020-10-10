public class FragFirst extends Fragment {

  private EditText UserNameEdittext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Button startbutton1 = (Button)findViewById(R.id.startbutton1);

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_first, container, false);

                UserNameEdittext = (EditText) rootView.findViewById(R.id.usernameText1);
                SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
                UserNameEdittext.setText(settings.getString("value", ""));

                startbutton1.setOnClickListener(new OnClickListener(){
                  @Override
                public void onClick(View rootView){
                  TspUserData.userName = UserNameEdittext.gettext().toString;
                  mPager.setCurrentItem(mPager.getCurrentItem()+1);
                }  
              });
        return rootView;
    }
  }