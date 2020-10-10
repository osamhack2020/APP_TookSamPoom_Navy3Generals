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
                TspUserData.Gender = MaleBtn.gettext().toString();
                mPager.setCurrentItem(mPager.getCurrentItem()+1);
            }
        });
        FemaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TspUserData.Gender = FemaleBtn.gettext().toString();
                mPager.setCurrentItem(mPager.getCurrentItem()+1);
            }
        });
        return rootView;
        
    }
}