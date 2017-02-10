package com.example.mengqi.hitmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Mengqi on 2/7/17.
 */

public class MainFragment extends Fragment {

    private static final String POPULARITY_ORDER = "https://api.themoviedb.org/3/movie/popular?api_key=a2fdd315a50fdfbfd7f570c3be23e740";
    String fakeData = "{\"page\":1,\"results\":[{\"poster_path\":\"\\/WLQN5aiQG8wc9SeKwixW7pAR8K.jpg\",\"adult\":false,\"overview\":\"The quiet life of a terrier named Max is upended when his owner takes in Duke, a stray whom Max instantly dislikes.\",\"release_date\":\"2016-06-18\",\"genre_ids\":[12,16,35,10751],\"id\":328111,\"original_title\":\"The Secret Life of Pets\",\"original_language\":\"en\",\"title\":\"The Secret Life of Pets\",\"backdrop_path\":\"\\/lubzBMQLLmG88CLQ4F3TxZr2Q7N.jpg\",\"popularity\":124.887405,\"vote_count\":2101,\"video\":false,\"vote_average\":5.8},{\"poster_path\":\"\\/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg\",\"adult\":false,\"overview\":\"From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.\",\"release_date\":\"2016-08-02\",\"genre_ids\":[28,80,14,878],\"id\":297761,\"original_title\":\"Suicide Squad\",\"original_language\":\"en\",\"title\":\"Suicide Squad\",\"backdrop_path\":\"\\/34dxtTxMHGKw1njHpTjDqR8UBHd.jpg\",\"popularity\":119.768921,\"vote_count\":4204,\"video\":false,\"vote_average\":5.9},{\"poster_path\":\"\\/z09QAf8WbZncbitewNk6lKYMZsh.jpg\",\"adult\":false,\"overview\":\"Dory is reunited with her friends Nemo and Marlin in the search for answers about her past. What can she remember? Who are her parents? And where did she learn to speak Whale?\",\"release_date\":\"2016-06-16\",\"genre_ids\":[16,10751],\"id\":127380,\"original_title\":\"Finding Dory\",\"original_language\":\"en\",\"title\":\"Finding Dory\",\"backdrop_path\":\"\\/iWRKYHTFlsrxQtfQqFOQyceL83P.jpg\",\"popularity\":94.489998,\"vote_count\":2172,\"video\":false,\"vote_average\":6.7},{\"poster_path\":\"\\/hLudzvGfpi6JlwUnsNhXwKKg4j.jpg\",\"adult\":false,\"overview\":\"Taking place after alien crafts land around the world, an expert linguist is recruited by the military to determine whether they come in peace or are a threat.\",\"release_date\":\"2016-11-10\",\"genre_ids\":[18,878],\"id\":329865,\"original_title\":\"Arrival\",\"original_language\":\"en\",\"title\":\"Arrival\",\"backdrop_path\":\"\\/yIZ1xendyqKvY3FGeeUYUd5X9Mm.jpg\",\"popularity\":82.017943,\"vote_count\":1720,\"video\":false,\"vote_average\":6.7},{\"poster_path\":\"\\/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg\",\"adult\":false,\"overview\":\"Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.\",\"release_date\":\"2015-06-09\",\"genre_ids\":[28,12,878,53],\"id\":135397,\"original_title\":\"Jurassic World\",\"original_language\":\"en\",\"title\":\"Jurassic World\",\"backdrop_path\":\"\\/dkMD5qlogeRMiEixC4YNPUvax2T.jpg\",\"popularity\":79.703364,\"vote_count\":5870,\"video\":false,\"vote_average\":6.5},{\"poster_path\":\"\\/uSHjeRVuObwdpbECaXJnvyDoeJK.jpg\",\"adult\":false,\"overview\":\"A teenager finds himself transported to an island where he must help protect a group of orphans with special powers from creatures intent on destroying them.\",\"release_date\":\"2016-09-28\",\"genre_ids\":[14],\"id\":283366,\"original_title\":\"Miss Peregrine's Home for Peculiar Children\",\"original_language\":\"en\",\"title\":\"Miss Peregrine's Home for Peculiar Children\",\"backdrop_path\":\"\\/9BVHn78oQcFCRd4M3u3NT7OrhTk.jpg\",\"popularity\":67.472194,\"vote_count\":1264,\"video\":false,\"vote_average\":6.3},{\"poster_path\":\"\\/rXMWOZiCt6eMX22jWuTOSdQ98bY.jpg\",\"adult\":false,\"overview\":\"Though Kevin has evidenced 23 personalities to his trusted psychiatrist, Dr. Fletcher, there remains one still submerged who is set to materialize and dominate all the others. Compelled to abduct three teenage girls led by the willful, observant Casey, Kevin reaches a war for survival among all of those contained within him—as well as everyone around him—as the walls between his compartments shatter apart.\",\"release_date\":\"2017-01-19\",\"genre_ids\":[18,27,53],\"id\":381288,\"original_title\":\"Split\",\"original_language\":\"en\",\"title\":\"Split\",\"backdrop_path\":\"\\/4G6FNNLSIVrwSRZyFs91hQ3lZtD.jpg\",\"popularity\":62.315398,\"vote_count\":458,\"video\":false,\"vote_average\":6.5},{\"poster_path\":\"\\/yNsdyNbQqaKN0TQxkHMws2KLTJ6.jpg\",\"adult\":false,\"overview\":\"Extreme athlete turned government operative Xander Cage (Vin Diesel) comes out of self-imposed exile, thought to be long dead, and is set on a collision course with deadly alpha warrior Xiang (Donnie Yen) and his team in a race to recover a sinister and seemingly unstoppable weapon known as Pandora's Box. Recruiting an all-new group of thrill-seeking cohorts, Xander finds himself enmeshed in a deadly conspiracy that points to collusion at the highest levels of world governments.\",\"release_date\":\"2017-01-13\",\"genre_ids\":[28,12,80,53],\"id\":47971,\"original_title\":\"xXx: Return of Xander Cage\",\"original_language\":\"en\",\"title\":\"xXx: Return of Xander Cage\",\"backdrop_path\":\"\\/6AewnVY9zBgVQEuCufLvsufeRcH.jpg\",\"popularity\":59.565862,\"vote_count\":339,\"video\":false,\"vote_average\":5.5},{\"poster_path\":\"\\/tNKdx3pWs3Y65juoujoqylWKiUO.jpg\",\"adult\":false,\"overview\":\"Julia becomes worried about her boyfriend, Holt when he explores the dark urban legend of a mysterious videotape said to kill the watcher seven days after viewing. She sacrifices herself to save her boyfriend and in doing so makes a horrifying discovery: there is a \\\"movie within the movie\\\" that no one has ever seen before.\",\"release_date\":\"2017-02-01\",\"genre_ids\":[27],\"id\":14564,\"original_title\":\"Rings\",\"original_language\":\"en\",\"title\":\"Rings\",\"backdrop_path\":\"\\/91WPDonXsxRzi7AcfedKM3p3NFU.jpg\",\"popularity\":54.677979,\"vote_count\":141,\"video\":false,\"vote_average\":5.2},{\"poster_path\":\"\\/ylXCdC106IKiarftHkcacasaAcb.jpg\",\"adult\":false,\"overview\":\"Mia, an aspiring actress, serves lattes to movie stars in between auditions and Sebastian, a jazz musician, scrapes by playing cocktail party gigs in dingy bars, but as success mounts they are faced with decisions that begin to fray the fragile fabric of their love affair, and the dreams they worked so hard to maintain in each other threaten to rip them apart.\",\"release_date\":\"2016-12-01\",\"genre_ids\":[10749,35,18,10402],\"id\":313369,\"original_title\":\"La La Land\",\"original_language\":\"en\",\"title\":\"La La Land\",\"backdrop_path\":\"\\/fp6X6yhgcxzxCpmM0EVC6V9B8XB.jpg\",\"popularity\":53.441936,\"vote_count\":1154,\"video\":false,\"vote_average\":8},{\"poster_path\":\"\\/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg\",\"adult\":false,\"overview\":\"Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.\",\"release_date\":\"2014-11-05\",\"genre_ids\":[12,18,878],\"id\":157336,\"original_title\":\"Interstellar\",\"original_language\":\"en\",\"title\":\"Interstellar\",\"backdrop_path\":\"\\/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg\",\"popularity\":41.923764,\"vote_count\":6945,\"video\":false,\"vote_average\":8},{\"poster_path\":\"\\/gri0DDxsERr6B2sOR1fGLxLpSLx.jpg\",\"adult\":false,\"overview\":\"In 1926, Newt Scamander arrives at the Magical Congress of the United States of America with a magically expanded briefcase, which houses a number of dangerous creatures and their habitats. When the creatures escape from the briefcase, it sends the American wizarding authorities after Newt, and threatens to strain even further the state of magical and non-magical relations.\",\"release_date\":\"2016-11-16\",\"genre_ids\":[12,10751,14],\"id\":259316,\"original_title\":\"Fantastic Beasts and Where to Find Them\",\"original_language\":\"en\",\"title\":\"Fantastic Beasts and Where to Find Them\",\"backdrop_path\":\"\\/6I2tPx6KIiBB4TWFiWwNUzrbxUn.jpg\",\"popularity\":36.330211,\"vote_count\":2033,\"video\":false,\"vote_average\":7},{\"poster_path\":\"\\/kqjL17yufvn9OVLyXYpvtyrFfak.jpg\",\"adult\":false,\"overview\":\"An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.\",\"release_date\":\"2015-05-13\",\"genre_ids\":[28,12,878,53],\"id\":76341,\"original_title\":\"Mad Max: Fury Road\",\"original_language\":\"en\",\"title\":\"Mad Max: Fury Road\",\"backdrop_path\":\"\\/phszHPFVhPHhMZgo0fWTKBDQsJA.jpg\",\"popularity\":31.78677,\"vote_count\":6510,\"video\":false,\"vote_average\":7.1},{\"poster_path\":\"\\/5N20rQURev5CNDcMjHVUZhpoCNC.jpg\",\"adult\":false,\"overview\":\"Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.\",\"release_date\":\"2016-04-27\",\"genre_ids\":[878,28],\"id\":271110,\"original_title\":\"Captain America: Civil War\",\"original_language\":\"en\",\"title\":\"Captain America: Civil War\",\"backdrop_path\":\"\\/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg\",\"popularity\":27.003186,\"vote_count\":4286,\"video\":false,\"vote_average\":6.8},{\"poster_path\":\"\\/5gJkVIVU7FDp7AfRAbPSvvdbre2.jpg\",\"adult\":false,\"overview\":\"A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.\",\"release_date\":\"2016-12-21\",\"genre_ids\":[12,18,10749,878],\"id\":274870,\"original_title\":\"Passengers\",\"original_language\":\"en\",\"title\":\"Passengers\",\"backdrop_path\":\"\\/5EW4TR3fWEqpKsWysNcBMtz9Sgp.jpg\",\"popularity\":24.996894,\"vote_count\":1047,\"video\":false,\"vote_average\":6.3},{\"poster_path\":\"\\/tIKFBxBZhSXpIITiiB5Ws8VGXjt.jpg\",\"adult\":false,\"overview\":\"Lynch discovers he is a descendant of the secret Assassins society through unlocked genetic memories that allow him to relive the adventures of his ancestor, Aguilar, in 15th Century Spain. After gaining incredible knowledge and skills he’s poised to take on the oppressive Knights Templar in the present day.\",\"release_date\":\"2016-12-21\",\"genre_ids\":[28,12,14,878],\"id\":121856,\"original_title\":\"Assassin's Creed\",\"original_language\":\"en\",\"title\":\"Assassin's Creed\",\"backdrop_path\":\"\\/r8aRipzR4wlDx0m7Bpi43Q84imc.jpg\",\"popularity\":24.337173,\"vote_count\":975,\"video\":false,\"vote_average\":5.2},{\"poster_path\":\"\\/n8WNMt7stqHUZEE7bEtzK4FwrWe.jpg\",\"adult\":false,\"overview\":\"Rachel Watson, devastated by her recent divorce, spends her daily commute fantasizing about the seemingly perfect couple who live in a house that her train passes every day, until one morning she sees something shocking happen there and becomes entangled in the mystery that unfolds.\",\"release_date\":\"2016-10-05\",\"genre_ids\":[53],\"id\":346685,\"original_title\":\"The Girl on the Train\",\"original_language\":\"en\",\"title\":\"The Girl on the Train\",\"backdrop_path\":\"\\/fpq86AP0YBYUwNgDvUj5kxwycxH.jpg\",\"popularity\":22.801216,\"vote_count\":771,\"video\":false,\"vote_average\":6},{\"poster_path\":\"\\/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg\",\"adult\":false,\"overview\":\"A rogue band of resistance fighters unite for a mission to steal the Death Star plans and bring a new hope to the galaxy.\",\"release_date\":\"2016-12-14\",\"genre_ids\":[28,12,14,878],\"id\":330459,\"original_title\":\"Rogue One: A Star Wars Story\",\"original_language\":\"en\",\"title\":\"Rogue One: A Star Wars Story\",\"backdrop_path\":\"\\/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg\",\"popularity\":22.609416,\"vote_count\":2104,\"video\":false,\"vote_average\":7.2},{\"poster_path\":\"\\/oFOG2yIRcluKfTtYbzz71Vj9bgz.jpg\",\"adult\":false,\"overview\":\"After waking up in a hospital with amnesia, professor Robert Langdon and a doctor must race against time to foil a deadly global plot.\",\"release_date\":\"2016-10-13\",\"genre_ids\":[28,80,9648,878,53],\"id\":207932,\"original_title\":\"Inferno\",\"original_language\":\"en\",\"title\":\"Inferno\",\"backdrop_path\":\"\\/anmLLbDx9d98NMZRyVUtxwJR6ab.jpg\",\"popularity\":21.996889,\"vote_count\":1122,\"video\":false,\"vote_average\":5.5},{\"poster_path\":\"\\/nHXiMnWUAUba2LZ0dFkNDVdvJ1o.jpg\",\"adult\":false,\"overview\":\"Vampire death dealer Selene fends off brutal attacks from both the Lycan clan and the Vampire faction that betrayed her. With her only allies, David and his father Thomas, she must stop the eternal war between Lycans and Vampires, even if it means she has to make the ultimate sacrifice.\",\"release_date\":\"2016-12-01\",\"genre_ids\":[28,27],\"id\":346672,\"original_title\":\"Underworld: Blood Wars\",\"original_language\":\"en\",\"title\":\"Underworld: Blood Wars\",\"backdrop_path\":\"\\/PIXSMakrO3s2dqA7mCvAAoVR0E.jpg\",\"popularity\":18.312994,\"vote_count\":715,\"video\":false,\"vote_average\":4.3}],\"total_results\":19564,\"total_pages\":979}";
    private GridViewAdapter mAdapter;
    GridView mGridView;
    Movie mMovie;
    private FragmentActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, container, false);
        mGridView = (GridView) view.findViewById(R.id.grid_view);

        ArrayList<Movie> movies = Utils.extractMovies(fakeData);
        mAdapter = new GridViewAdapter(getContext(), movies);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(new ScrollListener(getContext()));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieFragment fragment = new MovieFragment();
                Bundle args = new Bundle();
                args.putSerializable(Utils.KEY_MOVIE, mMovie);
                fragment.setArguments(args);
                mActivity = getActivity();
                mActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container, fragment)
                        .commit();

            }
        });
//        new MyAsyncTask(getActivity(), mGridView).execute();
        return view;
    }

//    private class MyAsyncTask extends AsyncTask<String, Void, List<Movie>> {
//        GridView mGridView;
//        Activity mContex;
//        public  MyAsyncTask(Activity contex,GridView gview)
//        {
//            this.mGridView=gview;
//            this.mContex=contex;
//        }
//
//
//        @Override
//        protected List<Movie> doInBackground(String... params) {
//            if (params.length < 1 || params[0] == null) {
//                return null;
//            }
//            List<Movie> fetchMovies = Utils.fetchMovieData(params[0]);
//            return fetchMovies;
//        }
//
//        @Override
//        protected void onPostExecute(List<Movie> data) {
//            mAdapter.clear();
//            if (data != null && !data.isEmpty()) {
//                mAdapter.addAll(data);
//            }
//        }
//    }
}
//    @Override
//    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
//        return new MovieLoader(getContext(),POPULARITY_ORDER);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
//        mAdapter.clear();
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<Movie>> loader) {
//        mAdapter.clear();
//    }
//}
