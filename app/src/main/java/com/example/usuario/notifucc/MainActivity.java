package com.example.usuario.notifucc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.usuario.notifucc.adapters.CardNotificacionAdapter;
import com.example.usuario.notifucc.servidor.BaseDeDatos;
import com.example.usuario.notifucc.servidor.Notificacion;
import com.example.usuario.notifucc.servidor.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SectionPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private RecyclerView recycler;
    private CardNotificacionAdapter adapter;
    private RecyclerView.LayoutManager lManager;

    public Usuario usuario;
    public TextView txtName;
    public LinearLayout linear;
    public ArrayList<Notificacion> listNotificaciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recibidos");
        listNotificaciones.add(new Notificacion("From: Jorge Castillo","Nueva fecha de examen","FISICA III","Nueva fecha de examen"));
        listNotificaciones.add(new Notificacion("From: Secretaría Ingeniería","Reunión docente","FACULTAD INGENIERÍA","Reunión docente"));
        listNotificaciones.add(new Notificacion("From: Alejandra Bosio","Entrega de prácticos","ING DE SOFTWARE","Entrega de prácticos"));
        listNotificaciones.add(new Notificacion("From: Martín Marcucci","Surgimiento proyecto","GRUPO DE INVESTIGACIÓN","Surgimiento proyecto"));
        listNotificaciones.add(new Notificacion("From: Gustavo Chiodi","Objetivos Académicos 2016","FACULTAD INGENIERÍA","Objetivos Académicos 2016"));

        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario)bundle.getSerializable("usuario");
        usuario.getApellido();

        mSectionsPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), SendMsgActivity.class);
                i.putExtra("usuario",usuario);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        inicializarRecycler();
    }

    public void inicializarRecycler(){
        //Obtener el recycler
        recycler = (RecyclerView)findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        //Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        //Crear un nuevo adaptador
        adapter = new CardNotificacionAdapter(this, listNotificaciones);
        recycler.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_recibidos) {
            getSupportActionBar().setTitle("Recibidos");
        } else if (id == R.id.nav_enviados) {
            getSupportActionBar().setTitle("Enviados");
        } else if (id == R.id.nav_subjects) {
            getSupportActionBar().setTitle("Materias");
        } else if (id == R.id.nav_group) {
            getSupportActionBar().setTitle("Grupos");
        } else if (id == R.id.nav_settings) {
            getSupportActionBar().setTitle("Configuración");
        } else if (id == R.id.nav_help) {
            getSupportActionBar().setTitle("Ayuda");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class RecibidosFragment extends Fragment implements AdapterView.OnItemSelectedListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private Spinner spi;
        public BaseDeDatos db = new BaseDeDatos();

        public RecibidosFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RecibidosFragment newInstance(int sectionNumber) {
            RecibidosFragment fragment = new RecibidosFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_recibidos, container, false);

            return rootView;
        }


        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a MateriasFragment (defined as a static inner class below).
            return RecibidosFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 1;
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
