# springboot-rest-example



https://docs.spring.io/spring-hateoas/docs/current/reference/html/#mediatypes


=======================================================================
HATEOAS
:: Hypermedia as the engine of application state

REST 요청에서 REST 요청할 수 있는 방법
"links"에 담아서 전달

- 링크를 만드는 기능
- 리소스를 만드는 기능 (리소스= 데이터+링크)
- 링크를 찾아주는 기능

링크
 - href
 - rel 
      - self
      - profile
      - ...
-------------------------------------------------------------------------
[Link]

:: Link link = new Link("/something", "my-rel");

= 기본링크 <link href="/somthing" rel="my-rel" />

:: Link link = new Link("/{segment}/something{?parameter}")
   Map<String, Object> values = new HashMap<>();
	values.put("segment", "path");
	values.put("parameter", 42);

  link.expand(values).getHref()

= 변수링크  " /{segment}/something{?parameter}"

** UriTemplate template으로 변수 url 생성 가능
========================================================================
[RepresentationModel]
  - [EntityModel]		단일 모델
  - [CollectionModel]	콜렉션 모델
        - [PagedModel]
---------------------------------------------------------------------
class PersonModel extends RepresentationModel<PersonModel> {
  String firstname, lastname;
}

PersonModel model = new PersonModel();
model.firstname = "Dave";
model.lastname = "Matthews";
model.add(new Link("https://myhost/people/42"));
----------------------------------------------------------------------
Person person = new Person("Dave", "Matthews");
EntityModel<Person> model = new EntityModel<>(person);
----------------------------------------------------------------------
Collection<Person> p = Collections.singleton(new Person("Dave", "Matt"));
CollectionModel<Person> model = new CollectionModel<>(p);
=======================================================================
[Link]
[UriTemplate]

[LinkRelation] 		- rel 연결관계
[RepresentationModel] 	- representation 모델
=======================================================================

절대 URI - 프로토콜, 호스트, 포트, 서블릿 값 필요

[WebMvcLinkBuilder]	- cotroller class로 Link생성

Link link = linkTo(PersonController.class).withRel("people");
-----------------------------------------------------------------------
HttpHeaders headers = new HttpHeaders();
headers.setLocation(linkTo(PersonController.class).slash(person).toUri());

return new ResponseEntity<PersonModel>(headers, HttpStatus.CREATED);











=======================================================================
SpringBoot Rest 
- 단순처리목적. MVC의 VC를 지움
- 도메인과 Respository만 REST API 제공






