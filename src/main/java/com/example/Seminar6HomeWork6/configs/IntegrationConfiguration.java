package com.example.Seminar6HomeWork6.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class IntegrationConfiguration {

    // Первый канал. От входных данных до трансформера
    @Bean
    public MessageChannel messageChannelInput(){
        return new DirectChannel();
    }

    // Второй канал. Передача данных от трансформера в модуль, который будет сохранять данные в файл
    @Bean
    public MessageChannel messageChannelFileWriter(){
        return new DirectChannel();
    }

    // Третий канал. Добавляем сам трансформер
    @Bean
    /**
     * myTransformer - создаем маршрутизатор
     * inputChannel - указываем, кто является входным файлом для данного блока
     * outputChannel - указываем, кто является выходным файлом
     */
    @Transformer(inputChannel = "messageChannelInput",
        outputChannel = "messageChannelFileWriter")
    public GenericTransformer<String, String> myTransformer(){
        return text -> { // получаем текст
            return text.toUpperCase(); // возвращаем текст большими буквами
        };
    }

    // Четвертый канал. Выход из нашей интеграции
    @Bean
    @ServiceActivator(inputChannel = "messageChannelFileWriter") // указываем, кто является входным файлом
    public FileWritingMessageHandler myFileWriter(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File("/D:\\GB\\Spring4462\\Seminar6\\Seminar6HomeWork6\\src\\main\\java\\com\\example\\Seminar6HomeWork6"));
        handler.setExpectReply(false); // показываем, что никого не ждет
        handler.setFileExistsMode(FileExistsMode.APPEND); // к файлу можем что-то дописывать
        handler.setAppendNewLine(true); // дописывать будем с новой строки
        return handler;
    }
}
