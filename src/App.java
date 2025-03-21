import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;

/*
 * В этом коде реализуется алгоритм Дейкстры, который состоит из 4 шагов.
 * Шаг 1. Найти узел с наименьшей стоимостью.
 * Шаг 2. Обновить стоимость соседей узла с наименьшей стоимостью.
 * Шаг 3. Повторить это для всех узлов.
 * Шаг 4. Когда все сделано, вывести итоговый путь.
 */

class App {
    // Граф, где ключ — это узел, а значение — это еще один HashMap, который хранит соседей и стоимость.
    private static HashMap<String, HashMap<String, Integer>> graph = new HashMap<>(); 

    // Граф, который хранит в себе стоимости узлов.
    private static HashMap<String, Integer> cost = new HashMap<>(); 

    // Граф, который показывает, какой узел чьим родителем является.
    private static HashMap<String, String> parents = new HashMap<>(); 

    // Хэш-таблица для хранения уже проверенных узлов.
    private static Set<String> checked = new HashSet<>();


    // Метод, который ищет узел с наименьшей стоимостью (Шаг 1).
    public static String findLowestNodeCost() {
        String lowestCostNode = null;     
        int lowestCost = Integer.MAX_VALUE; // Пока нам неизвестна стоимость, берем максимально возможную.
        
        // Цикл перебирает все узлы в графе.
        for (Map.Entry<String, Integer> entry : cost.entrySet()) {
            String node = entry.getKey();
            int costNode = entry.getValue();
            
            // Условие: если узел еще не проверен и его стоимость меньше текущей.
            if (!checked.contains(node)) {
                if (costNode < lowestCost) {
                    // Обновляем его значения.
                    lowestCost = costNode;
                    lowestCostNode = node;
                }
            }
        }
        // Возвращаем узел с наименьшей стоимостью.
        return lowestCostNode;
    }


    // Метод для обновления стоимостей и родителей узлов (Шаг 2).
    public static void updatedParents(String lowestCostNode) {
        // Получаем всех соседей узла с наименьшей стоимостью.
        for (Map.Entry<String, Integer> entry : graph.get(lowestCostNode).entrySet()) { 
            
            // Если у узла нет соседей, выходим из метода.
            if (graph.get(lowestCostNode) == null) return;

            String neighbor = entry.getKey(); // Соседний узел.
            int costToNeighbor = entry.getValue(); // Цена до соседнего узла.
            int newCostNeighbor = cost.get(lowestCostNode) + costToNeighbor; // Новая стоимость соседа.

            // Если новая стоимость дешевле (меньше) текущей, то обновляем ее.
            if (newCostNeighbor < cost.get(neighbor)) {
                cost.put(neighbor, newCostNeighbor);
                parents.put(neighbor, lowestCostNode);
            }
        }
    }


    // Основной метод алгоритма Дейкстры. Здесь все повторяется (Шаг 3).
    public static void dijkstra() {
        // Поиск узла с наименьшей стоимостью.
        String currentNode = App.findLowestNodeCost();

        // Пока не проверили все узлы, обновляем стоимость и родителей.
        while (currentNode != null) {
            updatedParents(currentNode); // Здесь обновляются соседи текущего узла.
            checked.add(currentNode); // Узел добавляется в список проверенных.
            currentNode = App.findLowestNodeCost(); // Затем переходим к следующему узлу.
        }
    }

    
    // Метод для вывода итогового пути (Шаг 4).
    public static List<String> getFinalPath() {
        // Список для хранения конечного пути.
        ArrayList<String> list = new ArrayList<>();
        // Переменная, чтобы начать путь от "end".
        String currentNode = "end";
        list.add(currentNode);
    
        // Идем по родителям узлов, чтобы восстановить путь.
        while (!currentNode.equals("start")) {
            // Получаем родителя текущего узла.
            String parentNode = parents.get(currentNode);
            
            // Добавляем его в список.
            list.add(parentNode);
            currentNode = parentNode;
        }
        Collections.reverse(list); // Разворачиваем список, чтобы путь шел от "start" до "end".
        return list; 
    }


    public static void main(String[] args) {
        // Добавляем в граф узлы и их соседей.
        graph.put("start", new HashMap<>());
        graph.get("start").put("A", 6);
        graph.get("start").put("B", 2);
        graph.put("A", new HashMap<>());
        graph.put("B", new HashMap<>());
        graph.get("A").put("end", 1);
        graph.get("B").put("A", 3);
        graph.get("B").put("end", 5);
        graph.put("end", new HashMap<>());

        // Добавляем в граф стоимости узлов.
        cost.put("start", 0);
        cost.put("A", 6);
        cost.put("B", 2);
        cost.put("end", Integer.MAX_VALUE);

        // Добавляем в граф родителей узлов.
        parents.put("A", "start");
        parents.put("B", "start");
        parents.put("end", null);

        // Запускаем сам алгоритм Дейкстры.
        dijkstra();

        // Выводим результат.
        System.out.println("Граф: " + graph);
        System.out.println("Стоимость узлов графа: " + cost);
        System.out.println("Родители соседей этого графа: " + parents);
        System.out.println("Кратчайший путь от старта до конца составляет: " + getFinalPath());
    }
}
