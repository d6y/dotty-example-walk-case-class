# Example walking a case class


Scenario is that I want to read CSV data (as a `List[String]`) into a case class:

```
trait Read[P] {
  def read(row: List[String]): Try[P]
}
```

For a given case class such as:

```
case class Foo(a: Int) derives Read
```

...I'd like to be able to be `given (reader: Read[Foo])` to be able to `reader.read(xs)` and get back a `Try[Foo]`.

The problem I have is in the `inline derived` of `example.scala`: I'm not matching on the `Shape` I'd expect.

```scala
  inline def derived[P] given (ev: Generic[P]) = new Read[P] {
    def read(row: List[String]): Try[P] = {
      inline erasedValue[ev.Shape] match {
        case _ : Case[_, elems] => Failure(new Exception("yay! it matched"))
        case _  => Failure(new Exception("it did not match... boo"))
      }
    }
  }
```

The output is:

```
sbt:example-cc> ~test
[debug] Test run started
[debug] Test ConverterSpec.lookup Read for Foo(Int) started
[error] Test ConverterSpec.lookup Read for Foo(Int) failed: 

   expected:<...ava.lang.Exception: [yay! it matched])>
    but was:<...ava.lang.Exception: [it did not match... boo])>, took 0.006 sec

```


Just two files: `example.scala` and `spec.scala`:


```
.
├── LICENSE.md
├── README.md
├── build.sbt
├── project
│   ├── build.properties
│   └── plugins.sbt
├── src
│   ├── main
│   │   └── scala
│   │       └── example.scala
│   └── test
│       └── scala
│           └── spec.scala
└── target

```
