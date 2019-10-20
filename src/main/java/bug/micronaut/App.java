package bug.micronaut;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.convert.exceptions.ConversionErrorException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.exceptions.ConversionErrorHandler;
import io.micronaut.http.server.netty.converters.UnsatisfiedRouteHandler;
import io.micronaut.runtime.Micronaut;
import io.micronaut.web.router.exceptions.UnsatisfiedRouteException;

import javax.inject.Singleton;

public class App {

  @Controller("/example/bug")
  public static class Bug {

    @Get
    public String get(@Header("hh") long hh) {
      return "get: hh=" + hh;
    }

    @Post
    public String post(@Header("hh") long hh, @Body User user) {
      return "post: hh=" + hh + " " + user;
    }

    @Put
    public String put(@Header("hh") long hh, @Body User user) {
      return "put: hh=" + hh + " " + user;
    }

    @Delete
    public String delete(@Header("hh") long hh) {
      return "delete: hh=" + hh;
    }
  }

  public static final class User {
    private final String id;
    private final String name;

    @JsonCreator
    public User(@JsonProperty("id") String id, @JsonProperty("name") String name) {
      this.id = id;
      this.name = name;
    }

    @Override
    public String toString() {
      return "User{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
  }

  public static void main(String[] args) {
    Micronaut.run(App.class, args);
  }
}
