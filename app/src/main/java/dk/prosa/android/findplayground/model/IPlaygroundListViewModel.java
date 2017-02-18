package dk.prosa.android.findplayground.model;

import java.util.List;

/**
 * Created by andersgjetting on 18/02/2017.
 */

public interface IPlaygroundListViewModel {

    String getTotalCount();
    List<IPlaygroundViewModel> getPlaygroundModels();
}
