package kr.changtalk.webrtc.controller;

import elemental2.dom.RTCIceCandidate;
import elemental2.dom.RTCSessionDescription;
import kr.changtalk.webrtc.dto.AnswerRequest;
import kr.changtalk.webrtc.dto.OfferRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketController {

//    @Autowired
//    private SimpMessageSendingOperations simpMessagingTemplate;

    @MessageMapping("/join_room/{roomName}")
    @SendTo("/topic/welcome/{roomName}")
    public String joinRoom(@DestinationVariable(value = "roomName") String roomName) {
        log.info("roomName = " + roomName);

        return "welcome to " + roomName;
    }

    @MessageMapping("/offer/{roomName}")
    @SendTo("/topic/offer/{roomName}")
    public RTCSessionDescription sendOffer(@DestinationVariable(value = "roomName") String roomName,
        RTCSessionDescription request) {
        log.info(String.format("request = (sdp : %s, type : %s)", request.sdp, request.type));
        request.sdp += '\n';

        return request;
    }

    @MessageMapping("/answer/{roomName}")
    @SendTo("/topic/answer/{roomName}")
    public RTCSessionDescription sendAnswer(@DestinationVariable(value = "roomName") String roomName,
        RTCSessionDescription request) {
        log.info("request = " + request);

        return request;
    }

    @MessageMapping("/ice/{roomName}")
    @SendTo("/topic/ice/{roomName}")
    public RTCIceCandidate sendIce(@DestinationVariable(value = "roomName") String roomName,
        RTCIceCandidate request) {
        log.info("request = " + request);

        return request;
    }
}
