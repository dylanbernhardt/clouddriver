/*
 * Copyright 2016 Target, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.clouddriver.openstack.client

import com.netflix.spinnaker.clouddriver.openstack.deploy.exception.OpenstackProviderException
import org.openstack4j.api.exceptions.ServerResponseException
<<<<<<< HEAD:clouddriver-openstack/src/test/groovy/com/netflix/spinnaker/clouddriver/openstack/client/OpenstackImageV1ClientProviderSpec.groovy
import org.openstack4j.api.image.ImageService
import org.openstack4j.model.image.Image
import org.openstack4j.model.network.Subnet
=======
import org.openstack4j.api.image.v2.ImageService
import org.openstack4j.model.image.v2.Image
import org.openstack4j.openstack.image.v2.domain.GlanceImage
>>>>>>> b5c9524e0... fix(provider/openstack): Vendor upstream opstack4j fixes.:clouddriver-openstack/src/test/groovy/com/netflix/spinnaker/clouddriver/openstack/client/OpenstackImageV2ClientProviderSpec.groovy
import org.springframework.http.HttpStatus

class OpenstackImageV2ClientProviderSpec extends OpenstackClientProviderSpec {

  def "list images succeeds"() {
    setup:
    Map<String, String> filters = null
    ImageService imageService = Mock(ImageService)
<<<<<<< HEAD:clouddriver-openstack/src/test/groovy/com/netflix/spinnaker/clouddriver/openstack/client/OpenstackImageV1ClientProviderSpec.groovy
    List<Image> images = [Mock(Image)]
=======
    GlanceImage.Location imageLocation = new GlanceImage.Location(url: "http://example.com/image.iso")
    Image image = Mock(Image) {
      getLocations() >> [imageLocation]
    }
>>>>>>> b5c9524e0... fix(provider/openstack): Vendor upstream opstack4j fixes.:clouddriver-openstack/src/test/groovy/com/netflix/spinnaker/clouddriver/openstack/client/OpenstackImageV2ClientProviderSpec.groovy

    when:
    List<Subnet> result = provider.listImages(region, filters)

    then:
    1 * mockClient.images() >> imageService
    1 * imageService.listAll(filters) >> images

    and:
<<<<<<< HEAD:clouddriver-openstack/src/test/groovy/com/netflix/spinnaker/clouddriver/openstack/client/OpenstackImageV1ClientProviderSpec.groovy
    result == images
=======
    result[0] instanceof OpenstackImage
    result[0].location == imageLocation.url
>>>>>>> b5c9524e0... fix(provider/openstack): Vendor upstream opstack4j fixes.:clouddriver-openstack/src/test/groovy/com/netflix/spinnaker/clouddriver/openstack/client/OpenstackImageV2ClientProviderSpec.groovy
    noExceptionThrown()
  }

  def "list images exception"() {
    setup:
    Map<String, String> filters = null
    ImageService imageService = Mock(ImageService)
    Throwable throwable = new ServerResponseException('foo', HttpStatus.INTERNAL_SERVER_ERROR.value())

    when:
    provider.listImages(region, filters)

    then:
    1 * mockClient.images() >> imageService
    1 * imageService.listAll(filters) >> { throw throwable }

    and:
    OpenstackProviderException openstackProviderException = thrown(OpenstackProviderException)
    openstackProviderException.cause == throwable
  }

}
