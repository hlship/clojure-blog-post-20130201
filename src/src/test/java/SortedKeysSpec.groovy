import com.howardlewisship.MapUtils
import spock.lang.Specification

class SortedKeysSpec extends Specification {

  def "<none> returned when both maps are empty"() {

    expect:
    MapUtils.sortedKeyList([:], [:]) == "<none>"
  }

  def "keys from both maps are merged into sorted list"() {
    expect:
    MapUtils.sortedKeyList([fred: true], [barney: true, wilma: true]) == "barney, fred, wilma"
  }

  def "no separator for single key"() {
    expect:
    MapUtils.sortedKeyList([fred: true], [:]) == "fred"
  }

  def "duplicates between maps are ignored"() {
    expect:
    MapUtils.sortedKeyList([fred:true], [fred: false, barney: true]) == "barney, fred"
  }

  def "keys may be other than string"() {
  when:
    def map1 = [:]
    def map2 = [:]

    map1[200] = true
    map2[[1, 2]] = true
    map2[Collection] = true

    then:
    MapUtils.sortedKeyList(map1, map2) == "200, [1, 2], interface java.util.Collection"
  }
}
