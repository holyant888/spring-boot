/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.metrics.cache;

import com.hazelcast.spring.cache.HazelcastCache;
import io.micrometer.core.instrument.binder.cache.HazelcastCacheMetrics;
import org.junit.jupiter.api.Test;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.predicate.RuntimeHintsPredicates;
import org.springframework.boot.actuate.metrics.cache.HazelcastCacheMeterBinderProvider.HazelcastCacheMeterBinderProviderRuntimeHints;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link HazelcastCacheMeterBinderProviderRuntimeHints}.
 *
 * @author Moritz Halbritter
 */
class HazelcastCacheMeterBinderProviderRuntimeHintsTests {

	private final HazelcastCacheMeterBinderProviderRuntimeHints sut = new HazelcastCacheMeterBinderProviderRuntimeHints();

	@Test
	void shouldRegisterHints() {
		RuntimeHints runtimeHints = new RuntimeHints();
		this.sut.registerHints(runtimeHints, getClass().getClassLoader());
		assertThat(RuntimeHintsPredicates.reflection().onMethod(HazelcastCache.class, "getNativeCache"))
				.accepts(runtimeHints);
		assertThat(RuntimeHintsPredicates.reflection().onType(HazelcastCacheMetrics.class)).accepts(runtimeHints);
	}

}
