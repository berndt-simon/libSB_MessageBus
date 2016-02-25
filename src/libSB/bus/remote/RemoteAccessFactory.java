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
package libSB.bus.remote;

import libSB.bus.MessageBus;
import libSB.bus.message.BoundMessageFactory;
import libSB.bus.message.MessageFactory;
import libSB.bus.message.callback.BoundCallbackFactory;
import libSB.bus.topic.Topic;
import libSB.bus.topic.Topics;

/**
 *
 * @author Simon Berndt
 */
public class RemoteAccessFactory {

    private final MessageBus messageBus;
    private final MessageFactory messageFactory;

    public RemoteAccessFactory(MessageBus messageBus, MessageFactory messageFactory) {
        this.messageBus = messageBus;
        this.messageFactory = messageFactory;
    }

    public <T> RemoteAccess<T> create(Topic topic, Class<T> type) {
        Topic callbackTopic = Topics.createCallbackSibling(topic);
        BoundCallbackFactory boundCallbackFactory = new BoundCallbackFactory(messageBus, callbackTopic, messageFactory);
        BoundMessageFactory<T> boundMessageFactory = new BoundMessageFactory<>(messageBus, topic, messageFactory, type);
        return new RemoteAccess<>(boundCallbackFactory, boundMessageFactory);
    }

}
