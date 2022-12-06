-- 사용자
CREATE TABLE User (
    user_id VARCHAR(70) NOT NULL,
    user_name VARCHAR(45),
    user_birth_date DATE,
    user_password VARCHAR(70),
    user_phone_num VARCHAR(70),
    PRIMARY KEY (user_id)
);

-- 자유 게시판
CREATE TABLE free_board (
    free_board_id int not null,
    free_board_user VARCHAR(70),
    free_board_title VARCHAR(100),
    free_board_content LONGTEXT,
    free_board_date DATE,
    primary key (free_board_id),
    FOREIGN KEY (free_board_user)
        REFERENCES User(user_id)
) ;

-- 문의사항 게시판
CREATE TABLE inquire (
    inquire_num int not null,
    inquire_title VARCHAR(100),
    inquire_content TEXT,
    inquire_user VARCHAR(70),
    inquire_date DATE,
    primary key (inquire_num),
    FOREIGN KEY (inquire_user)
        REFERENCES User(user_id)
) ;

-- 공지사항 
CREATE TABLE notice_board (
    notice_board_id int not null,
    notice_board_title VARCHAR(100),
    notice_board_content LONGTEXT,
    notice_board_date DATE,
    primary key (notice_board_id)
) ;

-- 홍보게시판
CREATE TABLE publicity_board (
    publicity_board_id int not null,
    publicity_board_user VARCHAR(70),
    publicity_board_title VARCHAR(100),
    publicity_board_content LONGTEXT,
    publicity_board_date DATE,
    primary key (publicity_board_id),
    FOREIGN KEY (publicity_board_user)
        REFERENCES User(user_id)
) ;

-- 팀원 모집 게시판
CREATE TABLE team_board (
    team_board_id int not null,
    team_board_user VARCHAR(70),
    team_recruit_post int,
    team_board_title VARCHAR(100),
    team_board_content LONGTEXT,
    team_board_date DATE,
    primary key (team_board_id),
    FOREIGN KEY (team_board_user)
        REFERENCES User(user_id),
    FOREIGN KEY (team_recruit_post)
        REFERENCES publicity_board(publicity_board_id)
) ;

-- 사용자
INSERT INTO user VALUES('kkdd12','이원석','2000-08-07','qwer1','010-0000-0000');
INSERT INTO user VALUES('kkdd13','박민관','1999-10-13','qwer2','010-0000-0001');
INSERT INTO user VALUES('kkdd14','박정훈','1998-04-05','qwer3','010-0000-0002');
INSERT INTO user VALUES('kkdd15','서아론','1999-12-11','qwer4','010-0000-0003');
INSERT INTO user VALUES('kkdd16','배선영','2000-06-07','qwer5','010-0000-0004');
INSERT INTO user VALUES('kkdd17','조채은','2001-11-12','qwer6','010-0000-0005');

-- 자유 게시판
INSERT INTO free_board VALUES('1','kkdd12','안녕','안녕하세요!','2022-10-15');
INSERT INTO free_board VALUES('2','kkdd13','반가워','반가워요!','2022-10-16');
INSERT INTO free_board VALUES('3','kkdd13','메뉴 추천','오늘 뭐 먹지','2022-10-16');
INSERT INTO free_board VALUES('4','kkdd14','자바 공부','같이 할 사람 있을까ㅠ','2022-10-17');
INSERT INTO free_board VALUES('5','kkdd15','ㅠㅠ','오늘 1교시 수업','2022-10-18');
INSERT INTO free_board VALUES('6','kkdd16','안녕하세요','다들 뭐 하시나요','2022-11-19');
INSERT INTO free_board VALUES('7','kkdd16','심심해','나랑 카톡ㄱ','2022-11-22');
INSERT INTO free_board VALUES('8','kkdd17','하이','오늘 같이 공부할 사람','2022-12-05');
INSERT INTO free_board VALUES('9','kkdd14','배고파','편의점 신상','2022-12-05');
INSERT INTO free_board VALUES('10','kkdd13','오늘 학식','메뉴 뭐야??','2022-12-05');

-- 홍보 게시판
INSERT INTO publicity_board VALUES('1','kkdd12','부산연합동아리','이번주 금요일 마감입니다!','2022-10-01');
INSERT INTO publicity_board VALUES('2','kkdd13','해커톤','무박2일 해커톤 진행해요','2022-10-02');
INSERT INTO publicity_board VALUES('3','kkdd14','코딩스터디','백준 알고리즘 문제 풀어요','2022-10-03');
INSERT INTO publicity_board VALUES('4','kkdd15','BOS 진로동아리','2학기 모집합니다','2022-10-04');
INSERT INTO publicity_board VALUES('5','kkdd16','봉사동아리','2학기 선착순 모집해요!','2022-10-05');
INSERT INTO publicity_board VALUES('6','kkdd17','코딩 창업동아리','이번 달까지 모집합니다','2022-10-06');
INSERT INTO publicity_board VALUES('7','kkdd17','통합 성과 경진대회','많은 관심 부탁드립니다','2022-11-10');
INSERT INTO publicity_board VALUES('8','kkdd13','캡스톤 디자인 대회','3,4학년만 신청 가능해요','2022-11-10');
INSERT INTO publicity_board VALUES('9','kkdd14','BOB 취업동아리','3,4학년 모집하고 있습니다','2022-11-10');
INSERT INTO publicity_board VALUES('10','kkdd15','ICPC 코딩 대회','이번 달 진행되는 대회입니다.','2022-11-10');

-- 팀원 모집 게시판
INSERT INTO team_board VALUES('1','kkdd12','1','동아리 지원','같이 하실 분 있나용','2022-10-11');
INSERT INTO team_board VALUES('2','kkdd13','1','개발 지원자','연합 동아리 코딩 같이 지원해요!','2022-11-12');
INSERT INTO team_board VALUES('3','kkdd14','2','해커톤 같이','같이 신청해요~','2022-11-13');
INSERT INTO team_board VALUES('4','kkdd15','2','해커톤 같이해요!!','혼자는 힘들 것 같아서ㅠㅠ','2022-11-14');
INSERT INTO team_board VALUES('5','kkdd16','3','스터디 지원','같이 하실 분 있을까요?','2022-11-15');
INSERT INTO team_board VALUES('6','kkdd15','3','코딩 스터디','스터디 같이 합시당','2022-11-16');
INSERT INTO team_board VALUES('7','kkdd14','4','진로동아리','1학년 중에 같이해요~~','2022-11-16');
INSERT INTO team_board VALUES('8','kkdd13','8','캡스톤','저희랑 같이 해요! 1명','2022-11-16');
INSERT INTO team_board VALUES('9','kkdd17','5','봉사동아리','봉사시간 필요한데 같이하실 분 있나요?!','2022-11-16');
INSERT INTO team_board VALUES('10','kkdd16','6','코딩 동아리','코딩 잘 하시는 분','2022-11-16');
INSERT INTO team_board VALUES('11','kkdd13','6','동아리 같이','지원해요','2022-11-16');
INSERT INTO team_board VALUES('12','kkdd13','9','취업동아리','팀원 구해요!','2022-11-16');
INSERT INTO team_board VALUES('13','kkdd13','7','경진대회 신청','같이 하실 분 있나요~~?','2022-11-16');
INSERT INTO team_board VALUES('14','kkdd13','10','코딩 대회','팀원 모집 합니당','2022-11-16');
INSERT INTO team_board VALUES('15','kkdd13','10','ICPC','컴공학생 모집해요!','2022-11-16');

-- 공지사항
INSERT INTO notice_board VALUES('1','2학기 등록금 분할3차 납부 안내','등록금 분할납부자납부기간 : 2022. 10. 26(수) ∼ 2022. 10. 27(목)','2022-10-20');
INSERT INTO notice_board VALUES('2','D-GRIT예비조사 실시안내','"D-GRIT"의 문항개정을 위해예비조사를 실시하고있습니다.','2022-10-24');
INSERT INTO notice_board VALUES('3','동의대학교 대학일자리플러스센터 및 국가고용정책 인식도 조사','대학일자리플러스센터 및 국가고용정책 인식도 조사 참여바랍니다.','2022-10-30');
INSERT INTO notice_board VALUES('4','박물관 특별전 개최 안내','동의대학교 여러분들의 많은 참여로 공모 받은 국가 지정 문화유산 사진전을 개최합니다.','2022-11-01');
INSERT INTO notice_board VALUES('5','연구윤리 관련 가이드 발간 안내','대학연구자를 위한 이해충돌 예방 길잡이를 발간하였음을 알려드립니다.','2022-11-02');
INSERT INTO notice_board VALUES('6','연구윤리 포럼 개최 안내','연구윤리 포럼 개최를 안내하오니, 많은 참여를 요청드립니다.','2022-11-10');
INSERT INTO notice_board VALUES('7','후원의 집 현황','동의대학교 발전을 위해 소중한 기부에 동참하는 업체입니다.많은 이용을 바랍니다.','2022-11-11');
INSERT INTO notice_board VALUES('8','카풀 운영 잠정 보류 안내','대학혁신지원사업 사회적가치실현의 일환으로 운영된 카풀 운용을 다음과 같이 잠정 보류합니다.','2022-11-13');
INSERT INTO notice_board VALUES('9','중앙도로에서 좌회전 및 우회전 금지 안내','법정관 뒤쪽으로 돌아서 이용해주시기 바랍니다.','2022-11-15');
INSERT INTO notice_board VALUES('10','학칙 개정안 공고','학칙을 일부 개정함에 있어 관계자에게 의견을 듣고자 공고','2022-11-20');

-- 문의사항 게시판
INSERT INTO inquire VALUES('1','공지사항 업데이트','업데이트가 너무 느려요...','kkdd12','2022-10-15');
INSERT INTO inquire VALUES('2','채팅','1:1채팅도 있으면 좋을 것 같아요!','kkdd13','2022-10-15');
INSERT INTO inquire VALUES('3','동아리 모집','모집기간 끝난건 바로 삭제해주세요','kkdd14','2022-11-01');
INSERT INTO inquire VALUES('4','글 관리','게시판 글 관리 부탁드립니다','kkdd14','2022-11-12');
INSERT INTO inquire VALUES('5','지도','지도 확대 안되나요?','kkdd15','2022-11-20');
INSERT INTO inquire VALUES('6','홍보게시판','이상한 글 있는데요','kkdd13','2022-11-22');
INSERT INTO inquire VALUES('7','사용자 신고','자유게시판 글 관리해주세요','kkdd12','2022-12-01');
