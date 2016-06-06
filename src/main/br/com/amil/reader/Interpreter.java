package br.com.amil.reader;

import br.com.amil.model.EventType;
import br.com.amil.model.Match;
import br.com.amil.util.DateUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.compile;

public class Interpreter {

    private Match currentMatch;
    private Map<Integer, Match> matches;

    public Interpreter() {
        matches = new HashMap<>();
    }

    public void processLine(String line) throws InterpreterException {
        for (EventType eventType : EventType.values()) {
            Matcher matcher = compile(eventType.getRegex()).matcher(line);
            if (matcher.find()) {
                switch (eventType) {
                    case START_MATCH:
                        start(parseInt(matcher.group("id")), DateUtils.parseToLocalDateTime(matcher.group("date")));
                        break;
                    case PLAYER_KILL:
                        currentMatch.getPlayer(matcher.group("killer")).addKill(DateUtils.parseToLocalDateTime(matcher.group("date")), matcher.group("weapon"));
                        currentMatch.getPlayer(matcher.group("killed")).addDeath();
                        break;
                    case WORLD_KILL:
                        currentMatch.getPlayer(matcher.group("killed")).addDeath();
                        break;
                    case END_MATCH:
                        finish(parseInt(matcher.group("id")), DateUtils.parseToLocalDateTime(matcher.group("date")));
                        break;
                }
                break;
            }
        }
    }

    private void start(Integer id, LocalDateTime dateTime) throws InterpreterException {
        if (currentMatch == null || currentMatch.getEnd() != null) {
            currentMatch = new Match(id, dateTime);
        } else {
            throw new InterpreterException("You can not start!");
        }
    }

    private void finish(Integer id, LocalDateTime dateTime) throws InterpreterException {
        if (currentMatch != null && currentMatch.getEnd() == null && currentMatch.getId().equals(id)) {
            currentMatch.setEnd(dateTime);
            matches.put(currentMatch.getId(), currentMatch);
        } else {
            throw new InterpreterException("You can not finish!");
        }
    }

    public void print() {
        matches.values()
                .stream()
                .sorted()
                .forEachOrdered(match -> match.printMatchSummary());
    }
}
