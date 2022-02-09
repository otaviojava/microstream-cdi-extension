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
package one.microstream.cdi;


/**
 * This annotation indicates the operation that will be stored using Microstream automatically.
 * It is a high-level implementation to save either the Iterable and Map instances 
 * or the root itself, where you can set by StoreType.
 * By default, it is lazy, and using the EAGER only is extremely necessary.
 * The rule is: "The Object that has been modified has to be stored!".
 * So, to more tunning and optimization in the persistence process, 
 * you can always have the option to do it manually through 
 * the {@link one.microstream.storage.types.StorageManager#store(Object)} method.
 * Ref: https://docs.microstream.one/manual/storage/storing-data/index.html
 *
 */
public @interface Store {
}
