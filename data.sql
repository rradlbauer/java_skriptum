DROP TABLE IF EXISTS vaccinations;
DROP TABLE IF EXISTS patients;

CREATE TABLE `patients` (
  `id` bigint(20) NOT NULL,
  `birthdate` date NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ssn` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `patients`
--

INSERT INTO `patients` (`id`, `birthdate`, `name`, `ssn`) VALUES
(1, '1985-03-14', 'Alice Mercer', 1234567890),
(2, '1990-07-22', 'Bob Nakamura', 2345678901),
(3, '1978-11-05', 'Carol Oduya', 3456789012),
(4, '2002-01-30', 'David Petrova', 4567890123),
(5, '1965-09-17', 'Elena Rossi', 5678901234);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `vaccinations`
--

CREATE TABLE `vaccinations` (
  `id` bigint(20) NOT NULL,
  `agent` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `doctor` varchar(255) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `vaccinations`
--

INSERT INTO `vaccinations` (`id`, `agent`, `date`, `doctor`, `patient_id`) VALUES
(1, 'COVID-19 mRNA (Pfizer)', '2021-05-18', 'Sarah Johnson', 1),
(2, 'Influenza (Fluzone HD)', '2023-10-12', 'Sarah Johnson', 1),
(3, 'Hepatitis B', '2022-03-05', 'Maria Torres', 2),
(4, 'Tdap', '2020-09-21', 'Liam Nguyen', 2),
(5, 'COVID-19 mRNA (Moderna)', '2021-07-15', 'Robert Evans', 3),
(6, 'Shingrix Dose 1', '2022-08-30', 'Priya Patel', 3),
(7, 'Shingrix Dose 2', '2022-11-02', 'Priya Patel', 3),
(8, 'HPV (Gardasil 9)', '2019-04-22', 'Emily Clarke', 4);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nuglsbp5ykgwk71du7k8bxkeg` (`ssn`);

--
-- Indizes für die Tabelle `vaccinations`
--
ALTER TABLE `vaccinations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5c1laoeib68cuaodnp96yx9ov` (`patient_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `patients`
--
ALTER TABLE `patients`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `vaccinations`
--
ALTER TABLE `vaccinations`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `vaccinations`
--
ALTER TABLE `vaccinations`
  ADD CONSTRAINT `FK5c1laoeib68cuaodnp96yx9ov` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`id`);
