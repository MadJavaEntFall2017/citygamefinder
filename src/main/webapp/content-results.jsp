<%@include file="taglib.jsp"%>

<script type="text/javascript" class="init">
    $(document).ready( function () {
        $('#gameTable').dataTable();
    } );

</script>

<div class="container-fluid">
    <br>
    <h6>Search Results</h6>
    <table id="gameTable" class="display" cellspacing="0" width="100%">
        <thead>
            <th>Date</th>
            <th>Time</th>
            <th>Zip Code</th>
            <th>Location</th>
            <th>Home Team</th>
            <th>Away Team</th>
        </thead>

        <tbody>
            <c:forEach var="current" items="${games}">
                <tr>
                    <td>${current.date}</td>
                    <td>${current.time}</td>
                    <td>${current.zipCode}</td>
                    <td>${current.location}</td>
                    <td>${current.homeTeam}</td>
                    <td>${current.awayTeam}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


</div>

