package orthae.com.github.tokenexchangegateway.java;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Exchange {

  public Exchange(String token) {
    this.token = token;
  }

  private String token;
}
