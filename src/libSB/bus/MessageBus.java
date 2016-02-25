/* 
 * The MIT License
 *
 * Copyright 2016 Simon Berndt.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package libSB.bus;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import libSB.bus.endpoint.Endpoint;
import libSB.bus.message.Message;
import libSB.bus.topic.Topic;
import libSB.bus.topic.matcher.TopicMatcher;

/**
 *
 * @author Simon Berndt
 */
public interface MessageBus extends MessageBusService {
    
    @Override
    <C> void registerEndpoint(TopicMatcher topic, Endpoint<? extends C> endpoint, Class<C> type);
    
    <C> void raise(Topic topic, Message<? extends C> message, Class<C> type);
    
    default <C> CompletableFuture<Void> raiseAsync(Topic topic, Message<? extends C> message, Class<C> type) {
        return CompletableFuture.runAsync(() -> raise(topic, message, type));
    }
    
    default <C> CompletableFuture<Void> raiseAsync(Topic topic, Message<? extends C> message, Class<C> type, Executor asyncExecutor) {
        return CompletableFuture.runAsync(() -> raise(topic, message, type), asyncExecutor);
    }
}
