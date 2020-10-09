public class FragSecond extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_second, container, false);
            
        ImageButton MaleBtn = (ImageButton)findViewById(R.id.MaleBtn);
        ImageButton FemaleBtn = (ImageButton)findViewById(R.id.FemaleBtn);
        

        MaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        FemaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        return rootView;
        
    }
}