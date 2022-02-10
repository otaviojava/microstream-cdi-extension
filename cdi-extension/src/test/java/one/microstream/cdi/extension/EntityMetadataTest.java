/*
 *    Copyright 2021 Otavio Santana
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package one.microstream.cdi.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityMetadataTest {

    @Test
    public void shouldReturnEmptyFields() {
        EntityMetadata metadata = EntityMetadata.of(Cat.class);
        Assertions.assertTrue(metadata.getFields().isEmpty());
    }

    @Test
    public void shouldReturnEmptyFields2() {
        EntityMetadata metadata = EntityMetadata.of(Product.class);
        Assertions.assertTrue(metadata.getFields().isEmpty());
    }

    @Test
    public void shouldReturnIterable() {
        EntityMetadata metadata = EntityMetadata.of(Inventory.class);
        Assertions.assertFalse(metadata.getFields().isEmpty());
    }
}