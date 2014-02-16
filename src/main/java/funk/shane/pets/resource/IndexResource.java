package funk.shane.pets.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yammer.metrics.annotation.Timed;

import funk.shane.pets.view.IndexView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    @GET
    @Timed
    public IndexView index(){
        return new IndexView();
    }
}
