/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.fabric8.quickstarts.camel;

import java.util.List;

import org.apache.camel.CamelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CamelContext camelContext;

    @Test
    public void newOrderTest() {
        // Register a new User
        final User testUser = new User("appId", "appKey", "Test");
        ResponseEntity<String> userResponse = restTemplate.postForEntity("/camel-rest-3scale/users/greet", testUser, String.class);
        assertThat(userResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        String greeting = userResponse.getBody();
        assertThat(greeting).isEqualTo("\"Hello Test\"");

        // List Users
        ResponseEntity<List<User>> usersResponse = restTemplate.exchange("/camel-rest-3scale/users/list",
            HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){});
        assertThat(usersResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<User> users = usersResponse.getBody();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getName()).isEqualTo("Test");
    }
}
