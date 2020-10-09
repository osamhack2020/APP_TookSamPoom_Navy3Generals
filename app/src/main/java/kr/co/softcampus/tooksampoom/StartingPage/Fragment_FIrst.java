public class FragFirst extends Fragment {

  private EditText UserName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_first, container, false);
                UserName = (EditText) rootView.findViewById(R.id.usernameText1);
                SharedPreferences settings = this.getActivity().getSharedPreferences("PREFS", 0);
                UserName.setText(settings.getString("value", ""));
   
        return rootView;
    }
  }