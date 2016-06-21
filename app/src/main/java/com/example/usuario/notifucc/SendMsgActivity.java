package com.example.usuario.notifucc;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.notifucc.servidor.BaseDeDatos;
import com.example.usuario.notifucc.servidor.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SendMsgActivity extends AppCompatActivity {


    private Usuario usuario;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Enviar Notificacion");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Notificacion enviada con éxito",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_msg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class GruposFragment extends Fragment implements AdapterView.OnItemSelectedListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private Spinner spi;
        public BaseDeDatos db = new BaseDeDatos();

        public GruposFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GruposFragment newInstance(int sectionNumber) {
            GruposFragment fragment = new GruposFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_send_msg, container, false);
            spi = (Spinner) rootView.findViewById(R.id.spinner);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("Destinatario: ");

            spi.setOnItemSelectedListener(this);

            List<String> materias = new ArrayList<String>();
            materias.add("Analisis Matematico I");
            materias.add("Fisica III");
            materias.add("Sistemas Operativos");

            List<String> grupos = new ArrayList<String>();
            grupos.add("Grupo de investigación - Ingeniería");
            grupos.add("Facultad Ingeniería");
            grupos.add("Directores de Carreras");
            grupos.add("Profesores Ingeniería");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, grupos);

            dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            spi.setAdapter(dataAdapter);
            return rootView;
        }


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public static class ParticularFragment extends Fragment implements AdapterView.OnItemSelectedListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private Spinner spi;
        public BaseDeDatos db = new BaseDeDatos();

        public ParticularFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ParticularFragment newInstance(int sectionNumber) {
            ParticularFragment fragment = new ParticularFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_send_particular, container, false);

            EditText editText = (EditText) rootView.findViewById(R.id.et_destinatario);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("Destinatario: ");

            return rootView;
        }


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public static class MateriasFragment extends Fragment implements AdapterView.OnItemSelectedListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private Spinner spi;
        public BaseDeDatos db = new BaseDeDatos();

        public MateriasFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MateriasFragment newInstance(int sectionNumber) {
            MateriasFragment fragment = new MateriasFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_send_msg, container, false);
            spi = (Spinner) rootView.findViewById(R.id.spinner);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText("Destinatario: ");

            spi.setOnItemSelectedListener(this);

            List<String> materias = new ArrayList<String>();
            materias.add("Analisis Matematico I");
            materias.add("Fisica III");
            materias.add("Ingeniería de Software");

            ArrayAdapter<String> dataAdapter = dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, materias);

            dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

            spi.setAdapter(dataAdapter);
            return rootView;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

        /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a MateriasFragment (defined as a static inner class below).
            if(position == 0)
                return GruposFragment.newInstance(position + 1);
            else if (position == 1)
                return MateriasFragment.newInstance(position + 1);
            else
                return ParticularFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "GRUPO";
                case 1:
                    return "ASIGNATURA";
                case 2:
                    return "PARTICULAR";
            }
            return null;
        }
    }
}
