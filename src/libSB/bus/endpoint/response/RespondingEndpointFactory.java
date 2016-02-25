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
package libSB.bus.endpoint.response;

import java.util.concurrent.Executor;
import java.util.function.Supplier;
import libSB.bus.MessageBus;
import libSB.bus.endpoint.Endpoint;
import libSB.bus.message.Message;
import libSB.bus.message.MessageFactory;
import libSB.bus.topic.Topic;

/**
 *
 * @author Simon Berndt
 */
public class RespondingEndpointFactory {

    private final MessageBus replyBus;
    private final MessageFactory messageFactory;

    public RespondingEndpointFactory(MessageBus replyBus, MessageFactory messageFactory) {
        this.replyBus = replyBus;
        this.messageFactory = messageFactory;
    }

    public <C> Endpoint<Topic> endpointForRequestResponse(Supplier<? extends C> supplier, Class<C> type) {
        return (Message<? extends Topic> message) -> {
            Topic responseTopic = message != null ? message.getContend() : null;
            if (responseTopic != null) {
                Message<C> responseMessage = messageFactory.messageForContend(supplier.get());
                replyBus.raise(responseTopic, responseMessage, type);
            }
        };
    }

    public <C> Endpoint<Topic> endpointForAsyncRequestResponse(Supplier<? extends C> supplier, Class<C> type) {
        return (Message<? extends Topic> message) -> {
            Topic responseTopic = message != null ? message.getContend() : null;
            if (responseTopic != null) {
                Message<C> responseMessage = messageFactory.messageForContend(supplier.get());
                replyBus.raiseAsync(responseTopic, responseMessage, type);
            }
        };
    }

    public <C> Endpoint<Topic> endpointForAsyncRequestResponse(Supplier<? extends C> supplier, Class<C> type, Executor executor) {
        return (Message<? extends Topic> message) -> {
            Topic responseTopic = message != null ? message.getContend() : null;
            if (responseTopic != null) {
                Message<C> responseMessage = messageFactory.messageForContend(supplier.get());
                replyBus.raiseAsync(responseTopic, responseMessage, type, executor);
            }
        };
    }

}
