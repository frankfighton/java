package com.services.rest.providor;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.Type;
import com.services.rest.providor.AlternateMediaType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;


@Provider
@Consumes(AlternateMediaType.APPLICATION_XPROTOBUF)
@Produces(AlternateMediaType.APPLICATION_XPROTOBUF)
public class ProtobufferProvider implements MessageBodyReader<Message>,
    MessageBodyWriter<Message> {
  public boolean isReadable(
      Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return Message.class.isAssignableFrom(type);
  }

  public Message readFrom(
      Class<Message> type,
      Type genericType,
      Annotation[] annotations,
      MediaType mediaType,
      MultivaluedMap<String, String> httpHeaders,
      InputStream entityStream)
      throws IOException, WebApplicationException {
    try {
      Method newBuilder = type.getMethod("newBuilder");
      GeneratedMessage.Builder<?> builder = (GeneratedMessage.Builder<?>) newBuilder.invoke(type);
      return builder.mergeFrom(entityStream).build();
    } catch (Exception e) {
      throw new WebApplicationException(e);
    }
  }

  @Override
  public boolean isReadable(Class<?> type, java.lang.reflect.Type genericType,
      Annotation[] annotations, MediaType mediaType) {
    return false;
  }

  @Override
  public Message readFrom(Class<Message> type, java.lang.reflect.Type genericType,
      Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
      InputStream entityStream) throws IOException, WebApplicationException {
    return null;
  }


  public boolean isWriteable(Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return Message.class.isAssignableFrom(type);
  }



  public long getSize(
      Message m, Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return m.getSerializedSize();
  }

  public void writeTo(
      Message m, Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException,
      WebApplicationException {
    entityStream.write(m.toByteArray());
  }

}
