/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

/**
 * = Vert.x Auth - Authentication and Authorisation
 *
 * This Vert.x component provides interfaces for authentication and authorisation that can be used from
 * your Vert.x applications and can be backed by different providers.
 *
 * Vert.x auth is also used by vertx-web to handle its authentication and authorisation.
 *
 * To use this project, add the following dependency to the _dependencies_ section of your build descriptor:
 *
 * * Maven (in your `pom.xml`):
 *
 * [source,xml,subs="+attributes"]
 * ----
 * <dependency>
 *   <groupId>${maven.groupId}</groupId>
 *   <artifactId>${maven.artifactId}</artifactId>
 *   <version>${maven.version}</version>
 * </dependency>
 * ----
 *
 * * Gradle (in your `build.gradle` file):
 *
 * [source,groovy,subs="+attributes"]
 * ----
 * compile '${maven.groupId}:${maven.artifactId}:${maven.version}'
 * ----
 *
 * == Basic concepts
 *
 * _Authentication_ means verifying the identity of a user.
 *
 * _Authorisation_ means verifying a user has an authority.
 *
 * What the authority means is determined by the particular implementation and we don't mandate any particular model,
 * e.g. a permissions/roles model, to keep things very flexible.
 *
 * For some implementations an authority might represent a permission, for example the authority to access all printers,
 * or a specific printer. Other implementations must support roles too, and will often represent this by prefixing
 * the authority with something like `role:`, e.g. `role:admin`. Another implementation might have a completely
 * different model of representing authorities.
 *
 * To find out what a particular auth provider expects, consult the documentation for that auth provider..
 *
 * == Authentication
 *
 * To authenticate a user you use {@link io.vertx.ext.auth.AuthProvider#authenticate(io.vertx.core.json.JsonObject, io.vertx.core.Handler)}.
 *
 * The first argument is a JSON object which contains authentication information. What this actually contains depends
 * on the specific implementation; for a simple username/password based authentication it might contain something like:
 *
 * ----
 * {
 *   "username": "tim"
 *   "password": "mypassword"
 * }
 * ----
 *
 * For an implementation based on JWT token or OAuth bearer tokens it might contain the token information.
 *
 * Authentication occurs asynchronously and the result is passed to the user on the result handler that was provided in
 * the call. The async result contains an instance of {@link io.vertx.ext.auth.User} which represents the authenticated
 * user and contains operations which allow the user to be authorised.
 *
 * Here's an example of authenticating a user using a simple username/password implementation:
 *
 * [source,java]
 * ----
 * {@link examples.AuthCommonExamples#example1}
 * ----
 *
 * == Authorisation
 *
 * Once you have an {@link io.vertx.ext.auth.User} instance you can call methods on it to authorise it.
 *
 * to check if a user has a specific authority you use {@link io.vertx.ext.auth.User#isAuthorised}.
 *
 * The results of all the above are provided asynchronously in the handler.
 *
 * Here's an example of authorising a user:
 *
 * [source,java]
 * ----
 * {@link examples.AuthCommonExamples#example2}
 * ----
 *
 * And another example of authorising in a roles based model which uses `role:` as a prefix.
 *
 * Please note, as discussed above how the authority string is interpreted is completely determined by the underlying
 * implementation and Vert.x makes no assumptions here.
 *
 * === Caching authorities
 *
 * The user object will cache any authorities so subsequently calls to check if it has the same authorities will result
 * in the underlying provider being called.
 *
 * In order to clear the internal cache you can use {@link io.vertx.ext.auth.User#clearCache()}.
 *
 * === The User Principal
 *
 * You can get the Principal corresponding to the authenticated user with {@link io.vertx.ext.auth.User#principal()}.
 *
 * What this returns depends on the underlying implementation.
 *
 * == Creating your own auth implementation
 *
 * If you wish to create your own auth provider you should implement the {@link io.vertx.ext.auth.AuthProvider} interface.
 *
 * We provide an abstract implementation of user called {@link io.vertx.ext.auth.AbstractUser} which you can subclass
 * to make your user implementation. This contains the caching logic so you don't have to implement that yourself.
 *
 * If you wish your user objects to be clusterable you should make sure they implement {@link io.vertx.core.shareddata.impl.ClusterSerializable}.
 *
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@Document(fileName = "index.adoc")
@ModuleGen(name = "vertx-auth-common", groupPackage = "io.vertx")
package io.vertx.ext.auth;

import io.vertx.codegen.annotations.ModuleGen;
import io.vertx.docgen.Document;
