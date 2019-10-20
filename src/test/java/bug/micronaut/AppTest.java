package bug.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpHeaders;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
class AppTest {

  @Inject EmbeddedServer server;

  @Inject
  @Client("/")
  HttpClient client;

  @Test
  void get() {
    String res =
        client.toBlocking().retrieve(HttpRequest.GET("/example/bug").headers(this::addEmptyHh));
    System.out.println("response: " + res);
  }

  @Test
  void post() {
    String res =
        client
            .toBlocking()
            .retrieve(
                HttpRequest.POST("/example/bug", "{\"id\":1,\"name\":\"john\"}")
                    .headers(this::addEmptyHh));
    System.out.println("response: " + res);
  }

  @Test
  void put() {
    String res =
        client
            .toBlocking()
            .retrieve(
                HttpRequest.PUT("/example/bug", "{\"id\":1,\"name\":\"john\"}")
                    .headers(this::addEmptyHh));
    System.out.println("response: " + res);
  }

  @Test
  void delete() {
    String res =
        client.toBlocking().retrieve(HttpRequest.DELETE("/example/bug").headers(this::addEmptyHh));
    System.out.println("response: " + res);
  }

  private void addEmptyHh(MutableHttpHeaders mhh) {
    mhh.add("hh", "");
  }
}
