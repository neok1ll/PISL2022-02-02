package by.it.group973601.novogrodsky.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        Arrays.sort(events, (a, b) -> {
            if (a.start == b.start && a.stop == b.stop) {
                return 0;
            }
            if (a.start < b.start) {
                return -1;
            } else if (a.start == b.start) {
                if (a.stop < b.stop) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        });
        int i = 0;
        while (events[i].start < from) i++;
        int d;
        while (i < events.length && events[i].stop <= to) {
            result.add(events[i]);
            d = events[i].stop;
            i++;
            while (events[i].start < d) {
                i++;
                if (i == events.length) break;
            }
        }
        return result;                        //вернем итог
    }

    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }
    }
}