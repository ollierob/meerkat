# meerkat
Java financial library.

## Utils

Stand-alone, minimal-dependency libraries for:

* Dates - "partial" date-times (local date and timezone with optional local time)
* Numeric - big decimal fractions, percentages, curves and surfaces, numerical differentiation
* Collections - general utility methods
* Data - key/value providers
* Functions - collectors, suppliers, consumers, other functional utilities
* Objects - casting, self-typed interfaces

## Schema

General interfaces for:
* Identifiers - instruments, countries, currencies, markets, portfolios
* Money - generically typed per currency ID, interest calculations, exchange rates, prices
* Calendars - business days, settlement dates, holidays

External definitions of:
* ISOs - countries, currencies
* Instrument IDs - ISIN, CUSIP, FIGI and others
* Ratings - Bloomberg, Fitch, Moodys, S&P

Instrument definitions for varying types of:
* Bonds - fixed coupon, floating rate, variable rate, perpetual, convertible
* Equities - stocks, indexes, futures, options, swaps, dividends 
* FX - spots, forwards, swaps, options
* Interest-rate derivatives - interest rate swaps, bond options, interest rate futures, bond futures
* Money-market - classic repos, open repos, buy/sell back, certificate of deposit, t-bills, commercial paper

## Pricing

* Bonds - clean & dirty price
* WIP ...

## Risk

Positions and sensitivities:

* Bonds - dollar duration
* WIP ...
